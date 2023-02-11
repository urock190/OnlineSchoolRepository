package com.academy.serializationUtil;

import com.academy.models.Course;
import com.academy.util.Logger;

import java.io.*;

public class SerializationUtils {
    private static final Logger LOGGER = new Logger(SerializationUtils.class.getName());
    private static final String FILE_PATH = "src/com/academy/serializationUtil/serializedCourse.txt";
    public static void serializeToFile(Course course) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))){
            oos.writeObject(course);
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    public static Course deserializeCourseObj() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))){
            return (Course) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
