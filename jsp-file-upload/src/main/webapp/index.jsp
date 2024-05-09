<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer Photo</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<section>
<form action="fileuploadservlet" autocomplete="off" enctype="multipart/form-data" method="post">
<p>Upload</p>
<div>
<input type="text" name="file_name">
<input type="file" name="file2" id="file2" style="display: none;">
<label for="file2">Select Files</label>
</div>
<button><span>Upload</span><span class="uploading">Uploading...</span></button>
<div class="pr">
<progress max="100" value="0"></progress>
<span class="progress-indicator"></span>
</div>
</form>
</section>
    <% 
	String file_name = (String)request.getParameter("filename");
	if(file_name!=null) {
		out.println(file_name + " file uploaded successfully");
	}
	%>

</body>

</html>
