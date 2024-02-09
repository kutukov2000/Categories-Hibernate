package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

public class ImageUtil {
    public static String saveImage(String pathToImage) throws IOException {
        byte[] image = Files.readAllBytes(new File(pathToImage).toPath());

        String folderPath = "src/main/resources/images";
        String imageName = UUID.randomUUID() + ".jpg";
        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            try (FileOutputStream fos = new FileOutputStream(folderPath + File.separator + imageName)) {
                fos.write(image);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return imageName;
    }

    public static void deleteImage(String imageName){
        String folderPath = "src/main/resources/images/";
        Path filePath = Paths.get(folderPath+imageName);

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            System.err.println("Error deleting the file: " + e.getMessage());
        }
    }
}
