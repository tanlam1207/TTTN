package com.lamnhattan.example003.Model;

// import java.util.List;
import java.util.UUID;
    public class ProductTagModels {
    private UUID productId;
    private String productName;
    private String tagName;
    private String sku;
    private Long tagid;
    private double price;

    public ProductTagModels(UUID productId, String productName,Long tagid,String tagName,String sku,double price) {
        this.productId = productId;
        this.productName = productName;
        this.tagid = tagid;
        this.tagName = tagName;
        this.sku = sku;
        this.price = price;
    }

    // Các getters và setters
}


