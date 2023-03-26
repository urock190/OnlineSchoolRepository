CREATE DATABASE IF NOT EXISTS school_schema;
USE school_schema;

CREATE TABLE courses (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  PRIMARY KEY (`course_id`)
);

CREATE TABLE students (
  `student_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT '-',
  `email` varchar(45) DEFAULT '-',
  UNIQUE KEY `email_UNIQUE` (`email`)
);

CREATE TABLE `student_course_relation` (
  `student_id` int NOT NULL,
  `course_id` int NOT NULL,
  UNIQUE KEY `unique_stud_course` (`student_id`,`course_id`),
  CONSTRAINT `course_fk` FOREIGN KEY (`course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_fk` FOREIGN KEY (`student_id`) REFERENCES students (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE teachers (
  `teacher_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `last_name` varchar(30) NOT NULL,
  `phone` varchar(20) DEFAULT '-',
  `email` varchar(45) DEFAULT '-',
  `course_id` int DEFAULT NULL,
  UNIQUE KEY `email_UNIQUE` (`email`),
  CONSTRAINT `teacher_fk` FOREIGN KEY (`course_id`) REFERENCES courses (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `lectures` (
  `lecture_id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `amount` int DEFAULT '0',
  `description` varchar(200) DEFAULT NULL,
  `creation_date` datetime NOT NULL,
  `lecture_date` datetime NOT NULL,
  `teacher_id` int DEFAULT NULL,
  `course_id` int DEFAULT NULL,
  FOREIGN KEY (`course_id`) REFERENCES courses (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`teacher_id`) REFERENCES teachers (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `homeworks` (
  `homework_id` int unsigned PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `name` varchar(75) NOT NULL,
  `task` varchar(200) DEFAULT NULL,
  `number_of_tasks` tinyint unsigned DEFAULT NULL,
  `deadline` datetime NOT NULL,
  `lecture_id` int DEFAULT NULL,
  FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `additional_materials` (
  `material_id` int PRIMARY KEY NOT NULL,
  `name` varchar(200) NOT NULL,
  `resource_type` enum('URL','VIDEO','BOOK') NOT NULL,
  `lecture_id` int DEFAULT NULL,
  FOREIGN KEY (`lecture_id`) REFERENCES `lectures` (`lecture_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP procedure IF EXISTS getDataFromTable;

DELIMITER $$
CREATE PROCEDURE getDataFromTable (in table_name varchar(55))
BEGIN
    SET @sql = CONCAT('SELECT * FROM ', table_name);

    PREPARE tab FROM @sql;
    EXECUTE tab;
    DEALLOCATE PREPARE tab;
END$$
DELIMITER ;