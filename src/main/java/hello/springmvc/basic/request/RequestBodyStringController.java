package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyStringV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // Stream은 바이트코드이기 때문에 어떤 인코딩으로 변환할건지 지정해줘야함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
        // Spring mvc는 
        // InputStream(Reader)-http요청 메시지 바디의 내용을 직접 조회
        // OutputStream(Writer)- http 응답 메세지의 바디에 직접 결과 출력
        // 위 두 파라미터를 모두 지원
        // Stream은 바이트코드이기 때문에 어떤 인코딩으로 변환할건지 지정해줘야함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        log.info("messageBody={}",messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    // spring mvc의 MessageConverter 자동 작동
    // Spring MVC 내부에서 HTTP 메시지 바디를 읽어서 문자나 객체로 변환해서 전달해주는데. 이 때 HTTP 메시지 컨버터 (HttpMessageConverter)라는 기능을 사용
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        // HttpEntity = Http Header,Body 정보를 직접 조회
        // (요청 파라미터 조회하는 기능과 관계 없음/ @RequestParam,@ModelAttribute와 관계 없음)- 이 둘은 get 쿼리 스트링이나 post 폼 데이터 관련임
        String messageBody = httpEntity.getBody();
        log.info("messageBody={}",messageBody);
        return new HttpEntity<>("ok");

        // HttpEntity를 상속받은, 기능이 추가된 RequestEntity, ResponseEntity를 사용하기도 함.
    }

    // HttpEntity를 상속받은, 기능이 추가된 RequestEntity, ResponseEntity를 사용하기도 함.
    @PostMapping("/request-body-string-v4")
    public HttpEntity<String> requestBodyStringV4(RequestEntity<String> requestEntity) throws IOException {
        String messageBody = requestEntity.getBody();
        log.info("messageBody={}",messageBody);
        return new ResponseEntity<>("ok", HttpStatus.CREATED);
    }

    // 궁극적으로는 Httpbody에 담긴 메세지를 읽고, 다시 HttpBody에 응답 메세지를 넣고자 할 때
    // @RequestBody와 @ResponseBody를 이용하는 듯.
    @ResponseBody
    @PostMapping("/request-body-string-v5")
    public String requestBodyStringV5(@RequestBody String messageBody) throws IOException {
        log.info("messageBody={}",messageBody);
        return "ok";
    }
    
    /*
    최종 정리
        1. 요청 파라미터 조회시 : @RequestParam , @ModelAttribute 사용 
        2. HTTP 메세지 바디를 직접 조회 시 : @RequestBody 사용
     */
}
