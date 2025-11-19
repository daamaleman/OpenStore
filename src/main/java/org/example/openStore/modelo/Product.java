package org.example.openStore.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;
import org.openxava.annotations.Required;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Product {
    @Id
    @Hidden
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String oid;

    @Required
    @Column(length = 50)
    String name;

    @ManyToOne(optional = false)
    @DescriptionsList(descriptionProperties = "name")
    Category category;

    @Required
    @Money
    @PositivePrice
    BigDecimal price;

    @Required
    @Digits(integer = 5, fraction = 0)
    int stock;
}
