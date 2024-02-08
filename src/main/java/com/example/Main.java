package com.example;

import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.example.models.Category;
import com.example.models.CategoryImage;
import com.example.models.Product;
import com.example.utils.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        // addCategory();
        editCategory();
        getAllCategories();
    }

    private static void addProduct() {
        Scanner scanner = new Scanner(System.in);

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            Product product = new Product();

            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            product.setName(name);

            System.out.println("Enter description: ");
            String image = scanner.nextLine();
            product.setDescription(image);

            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            product.setPrice(price);

            Category category = new Category();
            System.out.println("Enter category id: ");
            int category_id = scanner.nextInt();
            category.setId(category_id);
            product.setCategory(category);

            context.save(product);
            context.getTransaction().commit();
        }
    }

    private static void editProduct() {
        Scanner scanner = new Scanner(System.in);

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            System.out.println("Enter product id: ");
            int id = scanner.nextInt();

            scanner.nextLine();

            Product product = (Product) context.get(Product.class, id);

            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            product.setName(name);

            System.out.println("Enter new description: ");
            String description = scanner.nextLine();
            product.setDescription(description);

            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            product.setPrice(price);

            Category category = new Category();
            System.out.println("Enter new category id: ");
            int category_id = scanner.nextInt();
            category.setId(category_id);
            product.setCategory(category);

            context.save(product);
            context.getTransaction().commit();
        }
    }

    private static void deleteProduct() {
        Scanner scanner = new Scanner(System.in);
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            System.out.println("Enter product id: ");
            int id = scanner.nextInt();

            Product product = (Product) context.get(Product.class, id);

            context.delete(product);
            context.getTransaction().commit();
        }
    }

    private static void getAllCategories() {
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

    private static void addCategory() {
        Scanner scanner = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            Category category = new Category();

            System.out.println("Enter name: ");
            String name = scanner.nextLine();
            category.setName(name);

            System.out.println("Enter path to image: ");
            String pathToImage = scanner.nextLine();
            CategoryImage image = new CategoryImage(pathToImage);
            image.setCategory(category);
            category.setImage(image);

            category.setDateCreated(calendar.getTime());

            context.save(category);
            context.getTransaction().commit();
        }
    }

    private static void editCategory() {
        Scanner scanner = new Scanner(System.in);

        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            System.out.println("Enter category id: ");
            int id = scanner.nextInt();

            scanner.nextLine();

            Category category = (Category) context.get(Category.class, id);

            System.out.println("Enter new name: ");
            String name = scanner.nextLine();
            category.setName(name);

            System.out.println("Enter new path to image: ");
            String pathToImage = scanner.nextLine();
            CategoryImage image = new CategoryImage(pathToImage);
            category.setImage(image);

            context.save(category);
            context.getTransaction().commit();
        }
    }

    private static void deleteCategory() {
        Scanner scanner = new Scanner(System.in);
        var sessionFactory = HibernateUtil.getSessionFactory();
        try (Session context = sessionFactory.openSession()) {
            context.beginTransaction();

            System.out.println("Enter category id: ");
            int id = scanner.nextInt();

            Category category = (Category) context.get(Category.class, id);

            context.delete(category);
            context.getTransaction().commit();
        }
    }
}