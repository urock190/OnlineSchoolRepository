use school_schema;

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