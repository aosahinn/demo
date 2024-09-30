package com.preschool.demo.mulakat.desingPatterns;

class Logger {
    private static Logger instance;

    // Private constructor to prevent instantiation from outside
    private Logger() {
    }

    // Lazy initialization (thread-safe)
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("Log: " + message);
    }
}

// Kullanım
public class SingletonPattern {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        logger.log("Bu bir log mesajıdır.");
    }
}
