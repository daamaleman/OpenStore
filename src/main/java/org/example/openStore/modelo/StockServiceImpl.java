package org.example.openStore.modelo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class StockServiceImpl implements StockService{
    @PersistenceContext
    EntityManager em;

    @Override
    public void decreaseStock(Integer productId, int quantity)
        throws OutOfStockException{

        Product p = em.find(Product.class, productId);

        if (p.getStock() < quantity){
            throw new OutOfStockException(
                "No hay suficiente stock para el producto: " + p.getName());
        }

        p.setStock(p.getStock() - quantity);
        em.merge(p);
    }
}
