package com.example;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.example.models.Category;
import com.example.utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        // AddCategory();

        getAllCategories();        
    }

    private static void getAllCategories(){
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            List<Category> categories = context.createQuery("from Category", Category.class)
                    .getResultList();

            for (Category category : categories) {
                System.out.println(category);
            }

            context.getTransaction().commit();
        }
    }

    private static void AddCategory() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            Category category = new Category();

            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            category.setName(name);

            System.out.println("Enter image: ");
            String image = scanner.nextLine();
            category.setImage(image);

            category.setDateCreated(calendar.getTime());

            context.save(category);
            context.getTransaction().commit();
        }
    }
}