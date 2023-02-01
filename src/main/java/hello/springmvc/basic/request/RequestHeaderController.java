package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @GetMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String,String> headerMap, // request header 전체 가져오고 싶을때
                          @RequestHeader("host")String host, // request header안의 특정 값만 가져오고 싶을때
                          @CookieValue(value="myCookie", required = false)String cookie){ // value="쿠키이름" 형식으로 사용
        // 하나의 key에 여러 value를 매핑해야 할 때 MultiValueMap
        log.info("request={}",request);
        log.info("response={}",response);
        log.info("httpMethod={}",httpMethod);
        log.info("locale={}",locale);
        log.info("headerMap={}",headerMap);
        log.info("host={}",host);
        log.info("myCookie={}",cookie);
        return "ok";
    }
}
