package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

// Logger class
public class Logger {
    private LogLevel logLevel;
    private Map<LogLevel, LogCommand> logCommands;

    public Logger(LogLevel logLevel) {
        this.logLevel = logLevel;
        this.logCommands = new HashMap<>();
        initializeLogCommands();
    }

    public void setLogLevel(LogLevel level) {
        this.logLevel = level;
    }

    // Initialize log commands for each log level
    private void initializeLogCommands() {
        logCommands.put(LogLevel.FATAL, new FatalLogCommand());
        logCommands.put(LogLevel.ERROR, new ErrorLogCommand());
        logCommands.put(LogLevel.WARN, new WarnLogCommand());
        logCommands.put(LogLevel.INFO, new InfoLogCommand());
    }

    // Get current time in a specific format
    private String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    // Log message with the specified log level
    public void log(String message, LogLevel level) {
        if (level.ordinal() <= logLevel.ordinal()) {
            LogCommand logCommand = logCommands.get(level);
            if (logCommand != null) {
                logCommand.execute(message);
            }
        }
    }
}
