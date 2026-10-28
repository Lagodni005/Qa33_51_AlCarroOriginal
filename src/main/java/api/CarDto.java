package api;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CarDto {
    private String serialNumber;
    private String manufacture;
    private String model;
    private String year;
    private String fuel;
    private Integer seats;
    private String CarClass;
    private double pricePerDay;
    private String about;
    private String city;
    private double lat;
    private double lng;
    private String image;
    private String owner;
}
