package com.library.patterns;

public class DatabaseConfig {

    private static volatile DatabaseConfig instance;

    private String dbUrl;
    private String dbUsername;
    private String dbPassword;
    private String dbDriverClassName;

    private DatabaseConfig() {
        this.dbUrl = "jdbc:h2:mem:librarydb";
        this.dbUsername = "sa";
        this.dbPassword = "";
        this.dbDriverClassName = "org.h2.Driver";
    }

    public static DatabaseConfig getInstance() {
        if (instance == null) {
            synchronized (DatabaseConfig.class) {
                if (instance == null) {
                    instance = new DatabaseConfig();
                }
            }
        }
        return instance;
    }

    public String getDbUrl() {
        return dbUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbDriverClassName() {
        return dbDriverClassName;
    }

    public void printConfig() {
        System.out.println("=== Database Configuration (Singleton) ===");
        System.out.println("URL: " + dbUrl);
        System.out.println("Username: " + dbUsername);
        System.out.println("Driver: " + dbDriverClassName);
        System.out.println("==========================================");
    }
}
