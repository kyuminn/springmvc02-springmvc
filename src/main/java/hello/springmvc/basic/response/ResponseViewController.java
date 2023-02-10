package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    //@ResponseBody가 없으면 뷰 리졸버가 실행되어서 뷰를 찾고 해당 이름의 뷰를 렌더링한다
    //@ResponseBody가 있으면 뷰 리졸버가 실행되지 않고 HTTP BODY에 메세지를 직접 입력한다.
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data","hello!");
        return mav;
    }

    // @Controller 가 달린 class + method가 String을 반환할때 그 String은 view의 논리적인 이름.
    @RequestMapping("/response-view-v2")
    public String responseViewV1(Model model){
        model.addAttribute("data","hello!!");
        return "response/hello";
    }

    // 권장하지 않음
    // request url과 같은 경로의 view로 반환해줌 (스프링 mvc가 관례적으로)
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model){
        model.addAttribute("data","hello!!!");
    }
}
