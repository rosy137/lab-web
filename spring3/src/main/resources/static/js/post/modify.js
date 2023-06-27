/**
 * modify.js
 * for modify page
 */
document.addEventListener('DOMContentLoaded', () =>{
    const btnUpdate = document.querySelector('#btnUpdate');
    const btnDelete = document.querySelector('#btnDelete');
    const modifyForm = document.querySelector('#postModifyForm');
    const id = document.querySelector('#id').value;
    console.log(id);
    
    btnUpdate.addEventListener('click', () => {
        const check = confirm('save?');
        if(check){
            modifyForm.action = './update?id=' + id;
            modifyForm.method = 'post';
            modifyForm.submit();
        }
    });
    
   
    
});