package dat3;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Person {

    private String firstName;
    private String lastName;
    private int yearOfBirth;

}
