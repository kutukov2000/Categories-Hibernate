package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;

import com.example.models.Category;
import com.example.models.CategoryImage;
import com.example.models.Product;
import com.example.models.ProductImage;
import com.example.utils.HibernateUtil;
import com.example.utils.ImageUtil;

public class Main {
    public static void main(String[] args) {
        editProduct();
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
            String description = scanner.nextLine();
            product.setDescription(description);

            System.out.println("Enter price: ");
            double price = scanner.nextDouble();
            product.setPrice(price);

            Category category = new Category();
            System.out.println("Enter category id: ");
            int category_id = scanner.nextInt();
            category.setId(category_id);
            product.setCategory(category);

            List<ProductImage> images = new ArrayList<>();

            System.out.println("Enter number of images: ");
            int numberOfImages = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfImages; i++) {
                System.out.println("Enter image path for image " + (i + 1) + ": ");
                String imagePath = scanner.nextLine();
                ProductImage image = new ProductImage(imagePath);
                image.setProduct(product);
                images.add(image);
            }

            product.setImages(images);

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

            List<ProductImage> images = new ArrayList<>();

            System.out.println("Enter number of images: ");
            int numberOfImages = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < numberOfImages; i++) {
                System.out.println("Enter image path for image " + (i + 1) + ": ");
                String imagePath = scanner.nextLine();
                ProductImage image = new ProductImage(imagePath);
                image.setProduct(product);
                images.add(image);
            }

            product.setImages(images);

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
            String newImageName = ImageUtil.saveImage(pathToImage);
            CategoryImage image = (CategoryImage) context.get(CategoryImage.class, category.getImage().getId());
            image.setImage(newImageName);
            category.setImage(image);

            context.save(category);
            context.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
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