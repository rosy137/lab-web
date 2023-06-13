/**
 * array_function.js
 */

 document.addEventListener('DOMContentLoaded', function () {
     const numbers = [1,2,3,4,5,6,7];
     // 배열 concat() 메서드: 배열에 새로운 원소를 끝에 추가.
     // 배열 push() 메서드: 기존 배열의 끝에 원소를 추가. 리턴값은 없음.
     
     const arr = [];
     console.log(arr);
     arr.push(100);
     console.log(arr);
     arr.push(200);
     console.log(arr);
     
     let arr2 = [];
     console.log(arr2);
     arr2 = arr2.concat(100);
     console.log(arr2);
     
     // 1. numbers의 원소들 중에서 홀수들만 원소로 갖는 배열을 만들고 출력.
     // >> [1,3,5,7]
     /*
     const odds = [];
     for(let d of numbers){
        if(d % 2 == 1) {
            odds.push(d);
        }
     }
     console.log(odds);
     */
     
     let odds = [];
     for(let d of numbers){
         if(d % 2){ // 2로 나눈 나머지가 있으면(홀수이면)
             odds = odds.concat(d);
         }
     }
     console.log(odds);
     
     odds = numbers.filter((x) => x % 2);
     console.log(odds);
    
     // 2. numbers의 원소를 제곱한 숫자들을 원소로 갖는 배열을 만들고 출력
     // >> [1,4,9,16,25,36,49]
     const double = [];
     for(let d of numbers){
         double.push(d ** 2); // 거듭 제곱 연산자
     }
     console.log(double);
     
     squares = numbers.map((x) => x ** 2);
     console.log(squares);
     
     //배열의 원소들 중에서 홀수의 제곱을 원소로 갖는 배여를 만들고 출력
     // >> [1,9,25,49]
     const oddDouble = [];
     for(let d of numbers){
         if(d % 2 === 1){
             oddDouble.push(d*d);
         }
     }
     console.log(oddDouble);
     
     oddSquares = numbers.filter((x) => x % 2).map((x)=> x ** 2);
     console.log(oddSquares);
     
     
 });