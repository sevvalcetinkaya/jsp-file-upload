package com.filejsp.com;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class fileuploadservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file_name = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if(!isMultipartContent) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		RequestContext requestContext = new ServletRequestContext(request);
		try {
			List < FileItem > fields = upload.parseRequest(requestContext);
			Iterator < FileItem > it = fields.iterator();
			if(!it.hasNext()) {
				return;
			}
			while(it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if(isFormField) {
					if(file_name == null) {
						if(fileItem.getFieldName().equals("file_name")) {
							file_name = fileItem.getString();
						}
					}
				} else {
					if(fileItem.getSize() > 0) {
						fileItem.write(new File("C:\\odev4\\" + fileItem.getName()));
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='index.jsp?filename="+file_name+"'");
			out.println("</script>");
			out.close();
		}
	}

}
