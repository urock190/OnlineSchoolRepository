use school_schema;
#-------------------------------------------------Homework №40--------------------------------------------------
# ------------------------------------------------query №1
SELECT * FROM students order by last_name;

# ------------------------------------------------query №2
SELECT name, (select count(*) from additional_materials
        where additional_materials.lecture_id = lectures.lecture_id) as 'materials_number'
FROM lectures
where lecture_date < '2023-01-01'
order by lecture_date;

# ------------------------------------------------query №3
SELECT *
FROM lectures
where cast(creation_date as unsigned) = (select min(cast(creation_date as unsigned)) from lectures)
order by (select count(*) from homeworks where homeworks.lecture_id = lectures.lecture_id) desc
limit 1;

# ------------------------------------------------query №4
SELECT resource_type, COUNT(resource_type) AS quantity
FROM additional_materials
GROUP BY resource_type;

# ------------------------------------------------query №5 (the second A-M is for Ukrainian alphabet)
SELECT * FROM teachers
WHERE last_name REGEXP '^[A-MА-М]';

# ------------------------------------------------query №6
SELECT last_name, name, (SELECT COUNT(*)
     FROM student_course_relation
     WHERE student_id = students.student_id) AS courses_number
FROM students
GROUP BY last_name , name , courses_number
HAVING courses_number > 0
ORDER BY last_name;

#-------------------------------------------------Homework №41--------------------------------------------------
# ------------------------------------------------query №1
SELECT l.name, t.last_name AS teacher_lastname,
       t.name AS teacher_name
FROM lectures l
    JOIN teachers t ON l.teacher_id = t.teacher_id
ORDER BY l.lecture_date;

# ------------------------------------------------query №2
SELECT t.last_name, t.name, COUNT(l.teacher_id) AS lectures_number
FROM teachers t
    JOIN lectures l ON t.teacher_id = l.teacher_id
GROUP BY l.teacher_id;

# ------------------------------------------------query №3
SELECT l.lecture_date, l.name, t.teacher_id
FROM lectures l
    RIGHT JOIN teachers t ON l.teacher_id = t.teacher_id
WHERE t.teacher_id = 3
ORDER BY l.lecture_date;

# ------------------------------------------------query №4
SELECT c.name,
    COUNT(DISTINCT l.lecture_id) AS lectures_number,
    COUNT(DISTINCT t.teacher_id) AS teachers_number,
    COUNT(DISTINCT scr.student_id) AS students_number,
    COUNT(DISTINCT h.homework_id) AS homeworks_number,
    COUNT(DISTINCT am.material_id) AS materials_number
FROM courses c
        LEFT JOIN
    lectures l ON c.course_id = l.course_id
        LEFT JOIN
    teachers t ON c.course_id = t.course_id
        LEFT JOIN
    student_course_relation scr ON c.course_id = scr.course_id
        LEFT JOIN
    homeworks h ON l.lecture_id = h.lecture_id
        LEFT JOIN
    additional_materials am ON l.lecture_id = am.lecture_id
GROUP BY c.course_id;

# ------------------------------------------------query №5
SELECT MONTHNAME(lecture_date) AS monthName,
       COUNT(*) AS lectures_number
FROM lectures
GROUP BY monthName;

# ------------------------------------------------query №6
SELECT 'homeworks' AS data_type, COUNT(*) AS quantity
FROM homeworks
UNION
SELECT 'materials' AS data_type, COUNT(*) AS quantity
FROM additional_materials
ORDER BY quantity DESC
LIMIT 1;