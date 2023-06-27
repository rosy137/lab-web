/**
 * delete.js
 * for detail page
 */
document.addEventListener('DOMContentLoaded', () =>{
    const btnDelete = document.querySelector('#btnDelete');
    const deleteForm = document.querySelector('#postDeleteForm');
    const id = document.querySelector('input#id').value;
    console.log(id);
    
    btnDelete.addEventListener('click', () => {
        const check = confirm('delete?');
        if(check){
            deleteForm.action = './delete?id='+id;
            deleteForm.method = 'post';
            deleteForm.submit();
        }
    });
    
});