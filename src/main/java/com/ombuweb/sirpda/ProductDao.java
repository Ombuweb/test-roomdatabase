package com.ombuweb.sirpda;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert
    void insert(Product product);

    @Query("SELECT * FROM products")
    List<Product> getAllProducts();

    @Query("SELECT * FROM products WHERE inventory_id=:inventoryId")
    List<Product> findProductsForInventory(final int inventoryId);
}
