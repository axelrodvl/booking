package co.axelrod.booking.propertyservice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class Property implements Serializable {
    @Id
    private Long id;
    private String name;
    private String description;
    private String location;
    private int price;

    public Property(String name, String description, String location, int price) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.price = price;
    }
}
