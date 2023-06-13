/**
 * class.js
 */

 document.addEventListener('DOMContentLoaded',function () {
     
     // class 선언(정의)
     class Score {
         // 생성자
         constructor(korean, english, math) {
             // 필드(프로퍼티)의 선언과 초기화: 
             // class의 프로퍼티들을 선언할 때는 var, const, let과 같은 키워드를 사용하지 않는다.
             this.korean = korean;
             this.english = english;
             this.math = math;
         }
         // 클래스 생성자, 메서드 선언에서는 function 키워드를 사용하지 않음.
         sum() {
             return this.korean + this.english + this.math;
         }
         mean() {
             return this.sum() / 3;
         }
     }
     
     // 클래스의 객체 생성:
     const score1 = new Score(100,95,97);
     console.log(score1);
     console.log(score1.sum());
     console.log(score1.mean()); // class 사용시 프로퍼티와 메서드를 구분, 메서드로 아이콘이 표시됨 (객체 생성시 프로퍼티로 표시)
        
     // 자바스크립트 클래스 작성 연습
     // property: width, height
     // method: area, perimeter
     class Rectangle {
         constructor(width, height){
             this.width = width;
             this.height = height;
         }
         area() {
             return this.width * this.height;
         }
         perimeter() {
             return 2 * (this.width + this.height);
         }
     }
     const rect1 = new Rectangle(17,13);
     console.log(rect1);
     console.log(rect1.area());
     console.log(rect1.perimeter());
     
     
 });