package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello","age":20}
 * content-type=application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

    // json -> object transform
    private ObjectMapper objectMapper = new ObjectMapper();

    @PostMapping("/request-body-json-v1")
    public void requestBodyJson1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        response.getWriter().write("ok");
    }

    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJson2(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody);
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    // 다시 복습
    // httpbody에 있는 json타입의 데이터를 객체로 받고싶을때는 @RequestBody 또는 HttpEntity
    // get 방식의 쿼리스트링이나 post방식의 요청 파라미터를 받을때는 @ModelAttribute
    // 두 방식은 완전 다른 방식임.

    // HttpEntity 또는 @RequestBody 를 사용하면 HTTP 메시지 컨버터가 Http body의 메시지를 읽어들여서
    // String이나 객체로 자동 변환해준다 .
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJson3(@RequestBody HelloData helloData) throws IOException {
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    // @RequestBody 대신 HttpEntity도 가능.
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public String requestBodyJson4(HttpEntity<HelloData>  data) throws IOException {
        HelloData helloData = data.getBody();
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return "ok";
    }

    // HttpMessageConverter가 request뿐 아니라 response에도 application/json type으로 반환하도록 해준다
    @ResponseBody
    @PostMapping("/request-body-json-v5")
    public HelloData requestBodyJson5(@RequestBody HelloData helloData) throws IOException {
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return helloData;
    }
}
