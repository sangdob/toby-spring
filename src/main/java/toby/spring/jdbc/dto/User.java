package toby.spring.jdbc.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
public class User {
    private long id;
    private String name;
    private int age;
}
