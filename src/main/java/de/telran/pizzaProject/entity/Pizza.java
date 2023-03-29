package de.telran.pizzaProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Pizza {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull(message = "the field should NOT be empty")
    @NotBlank(message = "insert the correct name")
    @Size(min = 2, max = 20, message = "Insert the proper size")
    private String name;

    @Min(value = 5, message = "value should be more than 5.00")
    @Max(value = 30, message = "value should be less than 30.00")
    private double price;

    @Column(columnDefinition = "text")
    private String description;

    private String picture;

    @ManyToOne(optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cafe cafe;

}
