/**
 * function2.js
 * function 호출 방식
 */

test1(); // 위치 상관없이 호출 가능

function test1(){
    console.log('test1');
}

const test2 = () => console.log('test2');
test2(); // 아래에서만 호출 가능