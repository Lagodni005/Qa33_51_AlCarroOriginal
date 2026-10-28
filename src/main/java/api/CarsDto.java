package api;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CarsDto {
    private List<CarDto> cars;
}
