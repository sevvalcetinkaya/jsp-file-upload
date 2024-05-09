let file = document.getElementById('file2');
let button = document.getElementsByTagName('button');
let progress = document.querySelector('progress');
let p_i = document.querySelector('.progress-indicator');
let load = 0;
let process = "";
file.oninput = ()=>{
	let filename = file.files[0].name;
	document.querySelector('label').innerText = filename;
	getFile(filename);
}

let upload = ()=>{
	if(load >= 100){
		clearInterval(process);
		p_i.innerHTML = '100%' + ' ' +'Upload Completed';
	    button[0].classList.remove('active');
	}
	else {
		load++;
		progress.value = load;
		p_i.innerHTML = load + '%' + ' ' + 'Upload';
	    button[0].classList.remove('active');
	}
}
function getFile(fileName) {
	if(fileName) {
		document.querySelector('.pr').style.display = "block";
		load = 0;
		progress.value = 0;
		p_i.innerText = '';
		button[0].onclick = e=>{
			e.preventDefault();
			button[0].classList.add('active');
			process = setInterval(upload, 100);
		}
	}
}