package hello.springmvc.basic;

import lombok.Data;

@Data //@Getter, @Setter 등을 자동으로 적용해주는 애노테이션
public class HelloData {
    private String username;
    private int age;

}
