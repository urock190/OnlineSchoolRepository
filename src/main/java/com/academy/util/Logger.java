package com.academy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
    private final String name;
    private static List<Log> logs = new ArrayList<>();
    private static int levelValue;

    public Logger(String name) {
        this.name = name;
        levelValue = LogService.readLevelSetting().getValue();
    }

    public List<Log> getLogs() {return logs;}

    public Log getLog(int index) {return logs.get(index);}

    public boolean add(Log log) {return logs.add(log);}

    public Log remove(int index) {return logs.remove(index);}

    public void info(String message){
        if (!isLoggable(Level.INFO)) return;
        Log inf = new Log(this.name, Level.INFO, message);
        logs.add(inf);
        LogService.writeLog(inf);
    }

    public void debug(String message){
        if (!isLoggable(Level.DEBUG)) return;
        Log db = new Log(this.name, Level.DEBUG, message);
        logs.add(db);
        LogService.writeLog(db);
    }

    public void warning(String message, Throwable ex){
        if (!isLoggable(Level.WARNING)) return;
        Log warn = new Log(this.name, Level.WARNING, message);
        warn.setStacktrace(Arrays.toString(ex.getStackTrace()));
        logs.add(warn);
        LogService.writeLog(warn);
    }

    public void error(String message, Throwable ex){
        if (!isLoggable(Level.ERROR)) return;
        Log err = new Log(this.name, Level.ERROR, message);
        err.setStacktrace(Arrays.toString(ex.getStackTrace()));
        logs.add(err);
        LogService.writeLog(err);
    }

    public boolean isLoggable(Level level) {
        return level.getValue() >= levelValue;
    }

    public String getName() {
        return name;
    }

    public static int getLevelValue() {
        return levelValue;
    }

    public static void setLevelValue(int levelValue) {
        Logger.levelValue = levelValue;
    }
}
