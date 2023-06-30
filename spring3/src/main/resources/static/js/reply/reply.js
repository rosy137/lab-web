/**
 * 댓글 영역 보이기/숨기기 토글
 * 댓글 검색, 등록, 수정, 삭제
 */
document.addEventListener('DOMContentLoaded', () => {
    // 부트스트랩 Collapse 객체를 생성. 초기 상태는 화면에 보이지 않는 상태.
    const bsCollapse = new bootstrap.Collapse('div#replyToggleDiv', {toggle: false});
    
    // 토글 버튼을 찾고, 이벤트 리스너를 등록.
    const btnToggleReply = document.querySelector('#btnToggleReply');
    btnToggleReply.addEventListener('click', (e) => {
        bsCollapse.toggle();
        // console.log(e.target.innerText);
        if (e.target.innerText === 'SHOW') {
            e.target.innerText = 'HIDE';
            
            // 댓글 목록 불러오기:
            getRepliesWithPostId();
        } else {
            e.target.innerText = 'SHOW';
        }
    });
    
    //댓글 삭제 버튼 클릭을 처리하는 이벤트 리스너
    const deleteReply= (e) => {
        //console.log(e.target);
        const result = confirm('DELETE?');
        if(!result){
            return;
        }
        const id = e.target.getAttribute('data-id');
        const reqUrl = `/api/reply/${id}`;
        
        axios
            .delete(reqUrl) // ajax DELETE 요청
            .then((response) => { //then(function ()) 익명함수
                console.log(response);
                
                getRepliesWithPostId();
            }) // 성공 응답일 때 실행할 콜백 등록
            .catch((error) => console.log(error)); // 실패 응답일 때
    }
    
     const updateReply = (e) => {
        //console.log(e.target);
        const replyId = e.target.getAttribute('data-id'); // 수정할 댓글 아이디
        
        const textAreaId = `textarea#replyText_${replyId}`; // 댓글 입력 textarea 아이디
        //console.log(document.querySelector(textAreaId));
        
        // 수정할 댓글 내용
        const replyText = document.querySelector(textAreaId).value;
        if (replyText === '') { // 댓글 내용이 비어있으면
            alert('cannot insert blank');
            return;
        }
        
        const reqUrl = `/api/reply/${replyId}`; // 요청 주소
        const data = { replyText }; // {replyText: replyText}, 요청 데이터(수정할 댓글 내용)
        axios
            .put(reqUrl, data) // PUT 방식의 Ajax 요청을 보냄.
            .then((response) => {
                console.log(response);
                // TODO:
            }) // 성공 응답일 때 동작할 콜백을 등록.
            .catch((error) => console.log(error)); // 에러 응답일 때 동작할 콜백을 등록.
        
    };
    
    const makeReplyElements = (data) => {
        // 댓글 개수를 배열의 원소 개수로 업데이트
        document.querySelector('span#replyCount').innerText = data.length;
        
        // 댓글 목록을 삽입할 div 요소를 찾음.
        const replies = document.querySelector('div#replies');
        
        //div 안에 작성된 기존 내용은 삭제.
        replies.innerHTML = '';
        
        // div 안에 삽입할 HTML 코드 초기화
        let htmlStr = '';
        for(let reply of data) {
            htmlStr += `
            <div class="card mb-2 bg-transparent border-warning">
                <div class="row mt-2">
                    <div class="col">
                        <span class="fw-bold">#${reply.id}</span>
                        <span class="fw-bold">${reply.writer}</span>
                    </div>
                    <div class="col text-end">
                        <button class="btnModify btn btn-sm btn-warning" data-id="${reply.id}">MOD</button>
                        <button class="btnDelete btn btn-sm btn-outline-warning" data-id="${reply.id}">DEL</button>
                    </div>
                </div>
                <textarea id="replyText_${reply.id}" class="my-2 form-control border-warning bg-transparent">${reply.replyText}</textarea>
            </div>
            `;
        }
        
        //작성된 HTML 문자열을 div 요소의 innerHTML로 설정.
        replies.innerHTML = htmlStr;
        
        //모든 댓글 삭제 버튼들에게 이벤트 리스너를 등록
        const btnDeletes = document.querySelectorAll('button.btnDelete');
        for(let btn of btnDeletes){
            btn.addEventListener('click', deleteReply);
        }
        
        // 수정 버튼
        const btnModifies = document.querySelectorAll('button.btnModify');
        for(let btn of btnModifies){
            btn.addEventListener('click', updateReply);
        }
    };
    
    // 포스트 번호에 달려 있는 댓글 목록을 (Ajax 요청으로) 가져오는 함수:
    const getRepliesWithPostId = async () => {
        const postId = document.querySelector('input#id').value; // 포스트 아이디(번호)
        console.log(postId);
        const reqUrl = `/api/reply/all/${postId}`; // Ajax 요청 주소
        
        // Ajax 요청을 보내고 응답을 기다림.
        try {
            const response = await axios.get(reqUrl);
            // console.log(response);
            makeReplyElements(response.data);
        } catch (error) {
            console.log(error);
        }
    };
    
    // 댓글 등록 버튼을 찾고 이벤트 리스너 등록
    const btnReplyCreate = document.querySelector('button#btnReplyCreate');
    btnReplyCreate.addEventListener('click', () => {
        // 포스트 아이디
        const postId = document.querySelector('input#id').value;
        // 댓글 내용
        const replyText = document.querySelector('input#replyText').value;
        // 댓글 작성자(나중에 로그인한 사요ㅕㅇ자)
        const writer = 'admin';
        
        //alert(`${postId}, ${replyText}, ${writer}`);
        if(replyText === ''){
            alert('cannot input blank');
            return;
        }
        
        //Ajax 요청에서 보낼 데이터: /배열아니고 object, 
        const data = {postId, replyText, writer};
        
        //Ajax 요청을 보낼 URL
        const reqUrl = '/api/reply';
        
         axios
            .post(reqUrl,data)//Ajax POST 방식 요청을 보냄.
            .then((response) => {
                console.log(response);
                // 댓글목록 새로고침
                getRepliesWithPostId();
                // textarea 비우기
                document.querySelector('input#replyText').value = '';
            }) //성공 응답(response)일 때 실행할 콜백 등록
            .catch((error) => console.log(error)); //실패(error)일 때 실행할 콜백 등록.
            
    });
    
});