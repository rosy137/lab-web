/**
 * event.js
 * 
 * input value -> ul > li 로 생성
 * input 자리 지워주기
 */
document.addEventListener('DOMContentLoaded',function(){
    const itemInput = document.querySelector('input#itemInput');
    const btnInput = document.querySelector('button#btnInput');
    const itemList = document.querySelector('ul#itemList');
    btnInput.addEventListener('click',function(){
        // input 입력값 읽고
        const value = itemInput.value
        // input 값으로 li를 만든다.
        const item = `<li>${value}</li>`;
        // li요소를 ul에 추가
        itemList.innerHTML += item;
        
        // input의 값을 지운다.
        itemInput.value = '';
        itemInput.focus();
    });
    
    const itemInput2 = document.querySelector('input#itemInput2');
    const itemList2 = document.querySelector('ul#itemList2');
    
    itemInput2.addEventListener('keydown', function(event){
        //console.log(event); //-> event: keyboardEvent 객체
        
        if(event.key === 'Enter'){
            //console.log(itemInput2.value);
            const value = itemInput2.value;
            const item = `<li>${value}</li>`;
            itemList2.innerHTML += item;
            itemInput2.value = '';
            itemInput2.focus();
        }
        
    });
    
    const username = document.querySelector('input#username');
    const age = document.querySelector('input#age');
    const result = document.querySelector('div#result');
    
    username.addEventListener('change', function(event){
        //console.log(event);
        /*const value = `name : ${username.value} age : ${age.value}`;
        //console.log(value);
        result.innerHTML = value;*/
        updateNameAndAge();
    });
   
    age.addEventListener('change', function(event){
        updateNameAndAge();
    });
   
    function updateNameAndAge(){
        const name = username.value;
        const ag = age.value;
        const text = `<b>이름:</b> ${name} <b>나이:</b> ${ag}`;
        result.innerHTML = text;
    }
});