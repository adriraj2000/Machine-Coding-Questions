package org.example;

// Enum for log levels
enum LogLevel {
    FATAL,
    ERROR,
    WARN,
    INFO
}

// Command interface for log actions
interface LogCommand {
    void execute(String message);
}

// Concrete command implementations for different log levels
class FatalLogCommand implements LogCommand {
    @Override
    public void execute(String message) {
        System.out.println("[FATAL][" +  System.currentTimeMillis() + "] " + message);
    }
}

class ErrorLogCommand implements LogCommand {
    @Override
    public void execute(String message) {
        System.out.println("[ERROR][" + System.currentTimeMillis() + "] " + message);
    }
}

class WarnLogCommand implements LogCommand {
    @Override
    public void execute(String message) {
        System.out.println("[WARN][" +  System.currentTimeMillis()  + "] " + message);
    }
}

class InfoLogCommand implements LogCommand {
    @Override
    public void execute(String message) {
        System.out.println("[INFO][" +  System.currentTimeMillis()  + "] " + message);
    }
}

public class Main {
    public static void main(String[] args) {
        Logger logger = new Logger(LogLevel.INFO);

        logger.log("This is a fatal error.", LogLevel.FATAL);
        logger.log("This is an error message.", LogLevel.ERROR);
        logger.log("This is a warning message.", LogLevel.WARN);
        logger.log("This is an informational message.", LogLevel.INFO);

        // Changing log level at runtime
        logger.setLogLevel(LogLevel.ERROR);

        logger.log("This is a fatal error.", LogLevel.FATAL);
        logger.log("This is an error message.", LogLevel.ERROR);
        logger.log("This is a warning message.", LogLevel.WARN);
        logger.log("This is an informational message.", LogLevel.INFO);
    }
}