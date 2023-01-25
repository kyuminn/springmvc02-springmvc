package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 기존 @Controller = viewName 반환
 * @RestController = Rest API(= HTTP API) 만들때 핵심이 되는 Controller , 반환 값이 HTTP 메시지 바디에 바로 입력
 */
@RestController
@Slf4j
public class LogTestController {
    // import 하는 패키지 주의!
    // slf4j = 로거 라이브러리를 모두 모아놓은 인터페이스
    // 스프링 부트는 slf4j의 구현체로 logback 라이브러리를 사용한다.


    // lombok의 @Slf4j 사용하면 아래 줄 안써도 됨.
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping("/log-test")
    public String logTest(){
        String name = "Spring";
//        System.out.println("name = " + name); // 이제 sout은 지양할 것.
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}",name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);
        return "ok";
    }
}
