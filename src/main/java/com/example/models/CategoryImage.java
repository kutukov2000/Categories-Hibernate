package com.example.models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.example.utils.ImageUtil;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tbl_categories_images")
@NoArgsConstructor
public class CategoryImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false, length = 200)
    private String image;

    @OneToOne
    @JoinColumn(name = "category_id", nullable = false, unique = true)
    private Category category;

    public CategoryImage(String pathToImage) {
        try {
            image = ImageUtil.saveImage(pathToImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
