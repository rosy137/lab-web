/**
 * modify.js
 * for modify page
 */
document.addEventListener('DOMContentLoaded', () =>{
    const btnUpdate = document.querySelector('#btnUpdate');
    const modifyForm = document.querySelector('#postModifyForm');
    const id = document.querySelector('input#id').value;
    console.log(id);
    
    btnUpdate.addEventListener('click', () => {
		const title = document.querySelector('input#title').value;
		const content = document.querySelector('textarea#content').value;
		if(title === ''||content===''){
			alert('check title n content')
			return;
		}
		const check = confirm('save?')
		if(check){
			modifyForm.action = './update?id='+id;
			modifyForm.method = 'post';
			modifyForm.submit();
		}
	});
    
   
    
});