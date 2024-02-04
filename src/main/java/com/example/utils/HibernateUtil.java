package com.example.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.models.Category;

import lombok.Getter;

public class HibernateUtil {
    @Getter
    private static SessionFactory sessionFactory;

    static {
        try {
            Configuration conf = new Configuration().configure();
            conf.addAnnotatedClass(Category.class);
            sessionFactory = conf.buildSessionFactory();
        } catch (Exception ex) {
            System.out.println("DB initialization error " + ex.getMessage());
        }
    }
}
