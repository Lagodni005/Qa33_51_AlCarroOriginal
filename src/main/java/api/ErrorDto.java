package api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ErrorDto {
    private String timestamp;
    private int status;
    private String error;
    private Object message;
    private String path;
}
