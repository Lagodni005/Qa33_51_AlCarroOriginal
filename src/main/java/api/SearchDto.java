package api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class SearchDto {
    private String city;
    private String startDate;
    private String endDate;
}
