package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController //return을 그대로 반환한다.
public class LogTestController {
    //로그 선언 -> lombok이 제공해주는 @Slf4j를 통해 이 코드가 자동 생성돼있다
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        //단순 콘솔 출력
        System.out.println("name = " + name);
        //스레드로 실행되는 출력(로그 호출), 로그 레벨 순서대로 나열한 것임
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log={}", name);
        log.warn(" warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
