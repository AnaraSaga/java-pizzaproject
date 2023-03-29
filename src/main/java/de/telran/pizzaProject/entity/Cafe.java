package de.telran.pizzaProject.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Cafe {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull(message = "The field 'Name' is NOT filled")
    @NotBlank(message = "insert the proper name")
    @Size(min = 2, max = 20)
    private String name;

    @NotNull(message = "The field 'Address' is NOT filled")
    @NotBlank(message = "insert the proper address")
    private String address;

    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cafe")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Pizza> pizzasInCafe = new ArrayList<>();

}
