package dao_many_to_many.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Car {

    private final int ID;
    private String model;
    private String color;

}
