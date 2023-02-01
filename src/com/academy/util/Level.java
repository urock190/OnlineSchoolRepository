package com.academy.util;

public enum Level {
    OFF(Integer.MAX_VALUE), ERROR(1000), WARNING(900), INFO(800), DEBUG(700);
    private final int value;
    Level(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
