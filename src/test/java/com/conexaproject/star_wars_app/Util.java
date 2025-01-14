package com.conexaproject.star_wars_app;

import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.file.Files;

public class Util {

    public static String loadJsonFromFile(String fileName) throws IOException {
        // Usamos ClassPathResource para cargar archivos desde src/test/resources
        ClassPathResource resource = new ClassPathResource(fileName);
        return new String(Files.readAllBytes(resource.getFile().toPath()));
    }
}
