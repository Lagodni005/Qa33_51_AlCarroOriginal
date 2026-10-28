package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class Registration {
    private String name;
    private String lastName;
    private String email;
    private String password;

}
