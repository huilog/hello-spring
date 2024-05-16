package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component //컴포넌트 스캔
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))") //패키지 하위에 해당 AOP 모두 등록
    public Object execut(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toShortString());
        try {
            return joinPoint.proceed(); //다음 메서드로 진행됨
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toShortString() + " " + timeMs + "ms");

        }
    }
}
