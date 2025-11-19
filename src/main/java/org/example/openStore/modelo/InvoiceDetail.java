package org.example.openStore.modelo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.openxava.annotations.DescriptionsList;
import org.openxava.annotations.Hidden;
import org.openxava.annotations.Money;
import org.openxava.annotations.Required;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
public class InvoiceDetail {
    @Id
    @Hidden
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    String oid;

    @ManyToOne
    Invoice invoice;

    @ManyToOne(optional = false)
    @DescriptionsList(descriptionProperties = "name")
    Product product;

    @Required
    int quantity;

    @Money
    @Required
    BigDecimal price;

    @Money
    public BigDecimal getSubtotal() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @PrePersist
    @PreUpdate
    private void validateStock() {
        if (quantity <= 0){
            throw new InvoiceException("La cantidad debe ser mayor a 0");
        }

        StockService stockService = new StockService() {
            @Override
            public void decreaseStock(Integer productId, int quantity) throws OutOfStockException {
            // Validar y descontar stock usando XPersistence
            if (productId == null) throw new IllegalArgumentException("El producto es requerido");
            if (quantity <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");

            javax.persistence.EntityManager em = org.openxava.jpa.XPersistence.getManager();
            Product p = em.createQuery("select p from Product p where p.oid = :oid", Product.class)
                .setParameter("oid", String.valueOf(productId))
                .setMaxResults(1)
                .getResultStream()
                .findFirst()
                .orElse(null);

            if (p == null) throw new IllegalArgumentException("Producto no encontrado");
            if (p.getStock() < quantity) throw new OutOfStockException("Stock insuficiente");

            p.setStock(p.getStock() - quantity);
            }
        };
        stockService.decreaseStock(Integer.valueOf(product.getOid()), quantity);
    }
}

