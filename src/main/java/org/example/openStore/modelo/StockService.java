package org.example.openStore.modelo;

public interface StockService {
    void decreaseStock(Integer productId, int quantity) throws OutOfStockException;
}
