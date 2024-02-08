package com.example.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUtil {
    public static String saveImage(byte[] image) throws IOException {
        String folderPath = "src/main/resources/images";
        String imageName = UUID.randomUUID() + ".jpg";

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        try (FileOutputStream fos = new FileOutputStream(folderPath + File.separator + imageName)) {
            fos.write(image);
        }

        return imageName;
    }
}
