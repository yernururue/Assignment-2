package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

        System.out.println("========================================");
        System.out.println(" Library Management System Started!     ");
        System.out.println("========================================");
        System.out.println(" API Base URL: http://localhost:8080/api");
        System.out.println(" Database:     PostgreSQL (librarydb)   ");
        System.out.println("========================================");
    }
}
