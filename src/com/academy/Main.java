package com.academy;

import com.academy.services.MainService;

public class Main {
    public static void main(String[] args) {
        MainService.init();   // Creating 1 course & 3 lectures
        MainService.chooseCategoryAndCreateLecture();
    }
}