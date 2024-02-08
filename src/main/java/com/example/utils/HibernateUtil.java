package com.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.models.Category;
import com.example.models.CategoryImage;
import com.example.models.Product;
import com.example.models.ProductImage;

import lombok.Getter;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(Category.class);
            conf.addAnnotatedClass(CategoryImage.class);
            conf.addAnnotatedClass(Product.class);
            conf.addAnnotatedClass(ProductImage.class);
            sessionFactory = conf.buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("DB initialization error " + ex.getMessage());
        }
    }
}
