package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
// @RestController = @Controller + @ResponseBody
// class 단에 위와같이 어노테이션으로 설정하면 메소드에서 @ResponseBody를 쓰지 않아도 됨.
@Slf4j
@Controller
public class ResponseBodyController {

    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok!");
    }

    //ResponseEntity를 return하는 경우 상태코드(HttpStatus)를 지정해서 return할 수 있음
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(){
        return new ResponseEntity<>("ok!!", HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    // json data return
    // 조건에 따라 동적으로 HttpStatus code를 응답하고 싶을때는 @ResponseStatus 대신
    // ResponseEntity를 return 하는 방식으로 바꾸면 됨.
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/response-body-json-v1")
    public HelloData responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}
