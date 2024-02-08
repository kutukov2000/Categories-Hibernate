package com.example.models;

import java.io.IOException;

import javax.persistence.*;

import com.example.utils.ImageUtil;

import lombok.Data;

@Data
@Entity
@Table(name = "tbl_product_images")
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String image;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public ProductImage(String pathToImage) {
        try {
            image = ImageUtil.saveImage(pathToImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
