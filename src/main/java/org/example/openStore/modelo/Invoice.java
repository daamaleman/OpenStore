package org.example.openStore.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.ListProperties;
import org.openxava.annotations.Money;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
public class Invoice {
    @Id
    @Hidden
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String oid;

    @ManyToOne(optional = false)
    @DescriptionsList(descriptionProperties = "name")
    Customer customer;

    @Money
    BigDecimal total;

    @OneToMany(mappedBy = "invoice")
    @ListProperties("product.name, quantity, price, subtotal")
    java.util.Collection<InvoiceDetail> details;
}
