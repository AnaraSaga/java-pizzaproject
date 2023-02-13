package de.telran.pizzaProject.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pizza")
public class Pizza {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    private String name;

    private double price;

    private String description;

    private String image;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn (name ="pizza_id")
    private Cafe cafe;
}
