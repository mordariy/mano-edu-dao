package dao_many_to_many.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Person {

    private final int ID;
    private String name;
    private String surname;

}
