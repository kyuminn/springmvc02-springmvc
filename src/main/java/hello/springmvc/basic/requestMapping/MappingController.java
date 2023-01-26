package hello.springmvc.basic.requestMapping;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping({"/hello-basic","/hello"})
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }


    /**
     * 경로변수 - PathVariable 사용
     * url 예시 = /mapping/1
     * @param userId
     * @return
     */
    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String userId){
        log.info("mappingPath UserId = {}",userId);
        return "ok";
    }

    /**
     * 다중 PathVariable 사용
     * @param userId
     * @param orderId
     * @return
     */
    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable String userId, @PathVariable String orderId){
        log.info("mappingPath userId={} and orderId={}",userId,orderId);
        return "ok";
    }
    /**
     * 파라미터로 추가 매핑
     * params = "mode"
     * params = "!mode"
     * params = "mode=debug"
     * params = "mode!=debug"
     * params = {"mode=debug","data=good"}
     * 등등 여러방식으로 세팅 가능
     * 포스트맨에서 테스트 
     * request 파라미터에 mode=debug 값이 있어야 이 메서드가 호출됨
     * 올바르게 호출한 url 예시 = http://localhost:8080/mapping-param?mode=debug
     */

    @GetMapping(value = "/mapping-param", params = "mode=debug")
    public String mappingParam(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode"
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug"
     * 등의 방식으로 사용 가능
     *
     * @return
     */
    @GetMapping(value="/mapping-header",headers = "mode=debug")
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * 미디어 타입 조건 매핑
     * Http Request  Content-Type 헤더 기반 매핑
     * consumes=  "application/json"
     * consumes = "!application/json"
     * consumes = "application/*"
     * consumes = "*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */
    // 요청한 content type이 application/json 이어야만 메서드가 호출됨
    @PostMapping(value="/mapping-consume",consumes = "application/json")
    public String mappingConsumes(){
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * HTTP Request의 Accept 헤더 값을 필요로 함 
     * Accept 헤더 값 안에 있는 미디어 타입을 produces 할 수 있음 (클라이언트가 받아들일 수 있는 미디어 타입을 produce 해야 하므로)
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     * produces = MediaType.TEXT_PLAIN_VALUE
     * produces = "text/plain;charset=UTF-8"
     * @return
     */
    // 위와 반대로 생산하는 context type을 지정할 수 있음
    @PostMapping(value="/mapping-produce",produces = MediaType.TEXT_PLAIN_VALUE)
    public String mappingProduces(){
        log.info("mappingProduces");
        return "ok";
    }
}
