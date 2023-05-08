USE school_schema;

INSERT INTO courses (name) VALUES ('first course'), ('second course'), ('third course');

INSERT INTO students (name, last_name, phone, email)
VALUES ('Olena', 'Pchilka', '+380997777777', 'pchilka@ukr.net'),
       ('Fernando', 'Torres', '+380991277569', 'torresF@esp.net'),
       ('Olha', 'Kosach', '+380997777779', 'kosach@ukr.net'),
       ('Gwen', 'Stacy', '+380994447777', 'stacyGwen@gmail.com'),
       ('Aston', 'Martin', '+380665600977', 'astonMartin@gmail.com');

INSERT INTO student_course_relation (course_id, student_id)
VALUES (1, 2), (1, 3), (2, 3), (2, 4), (2, 5), (3, 1), (3, 2), (3, 4), (3, 5);

INSERT INTO teachers (name, last_name, phone, email, course_id)
VALUES ('Count', 'Dracula', DEFAULT, 'dracula@gmail.com', 1),
       ('Hakeem', 'Olajuwon', '1234567890', DEFAULT, 2),
       ('Vega', 'Nico', DEFAULT, 'nicovega@ddd.com', 3),
       ('Olena', 'Abakumova', '+380991080133', 'abakumova@ukr.net', 1),
       ('Shota', 'Aizawa', DEFAULT, 'eraserhead@gmail.com', 2);

INSERT INTO lectures (name, amount, description, creation_date, lecture_date, teacher_id, course_id)
VALUES ('lecture 1st', 3, 'some description', now(), '2023.11.03 15:00:00', 1, 1),
       ('lecture 2nd', 4, 'another description', now(), '2023.11.03 16:00:00', 2, 2),
       ('lecture 3rd', 5, 'just another description', now(), '2023.11.04 12:40:00', 3, 3),
       ('lecture 4th', 2, 'some description #4', now(), '2023.11.05 11:00:00', 4, 1),
       ('lecture 5th', 7, 'some description #5', now(), '2023.11.07 11:30:00', 5, 2);

INSERT INTO homeworks (homework_id, name, task, number_of_tasks, deadline, lecture_id)
VALUES (1, 'Homework #1', 'exercise 5, exercise 6', 2, '2023.11.04 12:00', 1),
       (2, 'Homework #2', 'ex.5, ex.7', 2, '2023.11.04 12:00', 2),
       (3, 'Homework #3', 'ex.21, read p.10, 11', 3, '2023.11.05 12:00', 3),
       (4, 'Homework #4', 'ex.11, 12, 14', 3, '2023.11.08 12:00', 5),
       (5, 'Homework #5', 'read chapter 2', 1, '2023.11.08 12:00', 5);

INSERT INTO additional_materials (material_id, name, resource_type, lecture_id)
VALUES (1, 'Organic chemistry', 'BOOK', 1),
       (2, 'Head First Java', 'BOOK', 2),
       (3, 'Holy Bible', 'BOOK', 3),
       (4, 'Big Video-course', 'VIDEO', 5),
       (5, 'https://comick.app/comic/one-punch-man/6WpdwU_I-chapter-181-en', 'URL', 2);

INSERT INTO lectures (lecture_id, name, amount, description, creation_date, lecture_date, teacher_id, course_id)
VALUE (6, 'lecture 6th', 10, DEFAULT, '2023-03-20 14:18:59', '2023-10-11 12:00:00', 3, 2);

INSERT INTO additional_materials (material_id, name, resource_type, lecture_id)
VALUE (6, 'https://github.com/urock190/OnlineSchoolRepository', 'URL', 4);