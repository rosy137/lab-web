package com.itwill.spring2.stream;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

// spring context(application-context.xml 또는 servlet-context.xml)를 사용하지 않는 
// 단위 테스트에서는 @ExtendWith, @ContextConfiguration 애너테이션을 사용할 필요가 없음.
public class StreamTest {
    @Test
    public void test() {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7);
        
        System.out.println(numbers);
        
        //numbers에서 홀수들만 필터링
        List<Integer> odds = numbers.stream()
                .filter((x) -> x % 2 ==1)
                .toList();
        System.out.println(odds);
        
        List<Integer> squares = numbers.stream()
                .map((x) -> x*x)
                //.map((x)->{return x*x;})
                .toList();
        System.out.println(squares);
        
        List<Integer> oddSquares = numbers.stream()
                .filter((x) -> x % 2 == 1)
                .map((x) -> x*x)
                .toList();
        System.out.println(oddSquares);
                
        
        List<String> languages = Arrays.asList("java","sql","javascript");
        System.out.println(languages);
        
        List<Integer> lengths = languages.stream()
                //.map((x) -> x.length())
                .map(String::length) // 아규먼트가 하나이고, 리턴값이 아규먼트에서 호출하는 것이거나 메서드에 호출될때
                .toList();
        System.out.println(lengths);
        
        List<LocalDateTime> times = Arrays.asList(
                LocalDateTime.of(2023, 5,
                        
                        23, 12, 30, 0),
                LocalDateTime.of(2023, 5, 24, 11, 30, 0),
                LocalDateTime.of(2023, 5, 25, 18, 30, 0)
        );
        System.out.println(times);
        
        List<Timestamp> timestamps = times.stream()
                .map(Timestamp::valueOf)
                .toList();
        System.out.println(timestamps);
    }
}
