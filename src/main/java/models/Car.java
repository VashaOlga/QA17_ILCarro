package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Car {

    String address;
    String make;
    String model;
    String year;
    String fuel;
    String seats;
    String carClass;
    String carRegNumber;
    String price;
}
