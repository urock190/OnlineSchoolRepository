package com.academy.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Logger {
    private final String name;
    private static List<Log> logs = new ArrayList<>();

    public Logger(String name) {
        this.name = name;
    }

    public List<Log> getLogs() {return logs;}

    public Log getLog(int index) {return logs.get(index);}

    public boolean add(Log log) {return logs.add(log);}

    public Log remove(int index) {return logs.remove(index);}

    public Log info(String message){
        Log inf = new Log(this.name, Level.INFO, message);
        logs.add(inf);
        LogService.writeLog(inf);
        return inf;
    }

    public Log debug(String message){
        Log db = new Log(this.name, Level.DEBUG, message);
        logs.add(db);
        LogService.writeLog(db);
        return db;
    }

    public Log warning(String message, Throwable ex){
        Log warn = new Log(this.name, Level.WARNING, message);
        warn.setStacktrace(Arrays.toString(ex.getStackTrace()));
        logs.add(warn);
        LogService.writeLog(warn);
        return warn;
    }

    public Log error(String message, Throwable ex){
        Log err = new Log(this.name, Level.ERROR, message);
        err.setStacktrace(Arrays.toString(ex.getStackTrace()));
        logs.add(err);
        LogService.writeLog(err);
        return err;
    }

    public String getName() {
        return name;
    }
}
