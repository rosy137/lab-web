/**
 * 포스트 업데이트 & 삭제
 */

document.addEventListener('DOMContentLoaded', () => {
    
    // <form> 요소를 찾음.
    const postModifyForm = document.querySelector('#postModifyForm');
    
    const btnUpdate = document.querySelector('#btnUpdate');
    btnUpdate.addEventListener('click', (e) => {
        const title = document.querySelector('#title').value;
        const content = document.querySelector('#content').value;
        if (title === '' || content === '') {
            alert('cant insert blank');
            return;
        }
        
        const result = confirm('UPDATE?')
        if (!result) {
            return;
        }
        
        postModifyForm.action = '/post/update';
        postModifyForm.method = 'post';
        postModifyForm.submit();
        
    });
    
    const btnDelete = document.querySelector('#btnDelete');
    btnDelete.addEventListener('click', (e) => {
        const result = confirm('DELETE?');
        if (!result) {
            return;
        }
        
        postModifyForm.action = '/post/delete'; // submit 요청 주소
        postModifyForm.method = 'post'; // submit 요청 방식
        postModifyForm.submit(); // 폼 제출(submit), 요청 보내기.
    });
});