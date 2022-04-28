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

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody //RestController 와 같은 효과
    @RequestMapping("request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") String memberAge) {
        log.info("username={}, age={}", memberName, memberAge);

        return "ok";
    }

    //v2와 v3가 다른 점은 HTTP 파라미터 이름이 변수명과 같다면, 파라미터를 생략할 수 있다는 점이다.
    @ResponseBody
    @RequestMapping("request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //String, int, Integer 등의 단순 타입이면 @RequestParam도 생략 가능(자동 적용)
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            //required=true인 조건의 파라미터는 반드시 있어야 함.
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            //defaultValue를 사용하면 기본값이 들어가기 때문에, required가 의미없다.
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    //파라미터를 Map으로 조회하기
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(
            @RequestParam Map<String, Object> paramMap){
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    //@ModelAttribute 적용 전
//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(
//            @RequestParam String username, @RequestParam int age){
//        HelloData helloData = new HelloData();
//        helloData.setUsername(username);
//        helloData.setAge(age);
//
//        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
//        log.info("helloData={}", helloData);
//        return "ok";
//    }

    //@ModelAttribute 적용 후
    //HelloData 객체가 생성되고, 요청 파라미터의 값도 모두 들어가있다.
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    //@ModelAttribute 생략
    //생략이 된다면 @RequestParam인지 @ModelAttribute인지 어떻게 알까?
    //String, int, Integer같은 단순 타입 -> @RequestParam
    //그 외 -> @ModelAttribute
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
