package com.academy;

import com.academy.services.MainService;
import com.academy.util.Logger;

public class Main {
    private static final Logger LOGGER = new Logger(Main.class.getName());
    public static void main(String[] args) {
        LOGGER.debug("Entering main() in class Main, creating 1 course & 3 lectures");
        MainService.init();
        LOGGER.debug("1 course and 3 lectures have been created");
        MainService.chooseCategoryAndCreateLecture();
        LOGGER.debug("Program finished");
    }
}