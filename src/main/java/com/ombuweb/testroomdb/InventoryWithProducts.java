package com.ombuweb.testroomdb;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class InventoryWithProducts {
    @Embedded
    public Inventory inventory;
    @Relation(
            parentColumn = "inventory_id",
            entityColumn = "inventory_id"
    )
    public List<Product> products;
}
