package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={},age={}",username,age);
        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody // 응답 데이터를 바로 http message body에 담고 싶을때 사용 (controller에 사용하면 @RestController)
    public String requestParamV2(@RequestParam("username")String memberName,
                                 @RequestParam("age")int memberAge){
        log.info("memberName={},memberAge={}",memberName,memberAge);
        return "okk";
    }
    
    
    // parameter name이랑 서버에서 받는 변수이름이 같으면 v3,4와 같이 축약된 형태로 사용 가능 (단 String,int ,Integer와 같은 단순타입인 경우에만)
    // 그치만 헷갈리니까 @RequestParam 명시하는게 더 좋은거 같음..
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age){
        log.info("username={},age={}",username,age);
        return "okk";
    }

    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username,int age){
        log.info("username={},age={}",username,age);
        return "okk";
    }

    // java의 Integer는 객체형이라서 null 가능
    // int는 null 불가 .
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age){
        log.info("username={},age={}",username,age);
        return "okk";
    }

    // defaultValue는 null뿐만아니라 빈문자열도 캐치
    // defaultValue가 있는 경우 required는 의미없음
    @RequestMapping("/request-param-default")
    @ResponseBody
    public String requestParamDefault(@RequestParam(required = true,defaultValue = "guest") String username,
                                       @RequestParam(required = false,defaultValue = "-1") int age){
        log.info("username={},age={}",username,age);
        return "okk";
    }

    // 요청 파라미터 map으로 조회하는 경우
    // 참고로 MultiValueMap으로도 받을 수 있음 (하나의 key에 여러 value가 들어가는 경우)
    // value가 하나만 있다는게 확실하면 Map을 쓰지만 왠만하면 MultiValueMap을 사용 ?
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestParamMap(@RequestParam Map<String,Object> paramMap){
        log.info("username={},age={}",paramMap.get("username"),paramMap.get("age"));
        return "okk";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return "okkk";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={},age={}",helloData.getUsername(),helloData.getAge());
        return "okkk";
    }
}
