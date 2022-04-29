package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1(){
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    //String을 반환하는 경우 - View 또는 HTTP 메시지
    // - @ResponseBody가 없으면 'response/hello'로 뷰 리졸버가 실행돼서 뷰를 찾고 렌더링됨
    // - @ResponseBody가 있으면 뷰 리졸버가 실행되지 않고, HTTP 메시지 바디에 직접 'response/hello'라는 문자가 입력됨
    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

}
