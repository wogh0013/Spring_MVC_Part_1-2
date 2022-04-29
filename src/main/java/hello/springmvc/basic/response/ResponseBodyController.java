package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@Controller
//@ResponseBody
@RestController //@Controller + @ResponseBody
public class ResponseBodyController {

    //문자 처리 ~~
    //response 객체를 통해 HTTP 메시지 바디에 직접 응답 메시지 전달
    @GetMapping("response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    //HttpEntity를 상속받은 ResponseEntity를 사용해 HTTP 메시지의 헤더, 바디 정보, 응답 코드까지 설정
    @GetMapping("response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    //HTTP 메시지 컨버터를 통해 HTTP 메시지 직접 입력 (view 사용 X)
//    @ResponseBody // HTTP 메시지 바디에 입력
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){
        return "ok";
    }

    //JSON 처리 ~~
    //HTTP 메시지 컨버터를 통해서 HTTP 메시지 직접 입력
//    @ResponseBody
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    //응답 코드를 동적으로 변경하고 싶으면 애너테이션 말고 ResponseEntity 사용
    @ResponseStatus(HttpStatus.OK) //HTTP 상태 코드 변경 애너테이션
//    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }


}
