package dao_many_to_many.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CarsDrivers {

    private Person person;
    private Car car;

}
