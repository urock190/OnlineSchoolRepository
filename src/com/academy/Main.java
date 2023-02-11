package com.academy;

import com.academy.models.Course;
import com.academy.serializationUtil.SerializationUtils;
import com.academy.services.CourseService;
import com.academy.services.MainService;
import com.academy.util.Level;
import com.academy.util.LevelWatcher;
import com.academy.util.LogService;
import com.academy.util.Logger;

public class Main {
    private static final Logger LOGGER = new Logger(Main.class.getName());
    public static void main(String[] args) {
        LogService.writeLevelSetting(Level.OFF);
        LOGGER.debug("Entering main() in class Main.");
        Thread lvlWatcher = new Thread(new LevelWatcher(), "level watcher");
        lvlWatcher.start();
        LOGGER.debug("Creating 1 course & 3 lectures.");
        MainService.init();
        LOGGER.debug("1 course and 3 lectures have been created");
//        MainService.chooseCategoryAndCreateLecture();
        CourseService courseService = new CourseService();
        Course course = SerializationUtils.deserializeCourseObj();
        courseService.printCourseInfo(course);
        lvlWatcher.interrupt();
        LOGGER.debug("Program finished");
    }
}