package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "product_name")
    private String productName;
    private Integer qty;
    private Integer price;

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setId(Long id){
        this.id = id;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public Integer getQty() {
        return qty;
    }

    public Integer getPrice() {
        return price;
    }
}
