CREATE TABLE courses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL UNIQUE,
    instructorEmail VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(10),
    inactivationDate DATE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;


CREATE TABLE registration (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    course_id BIGINT NOT NULL,
    enrollmentDate DATE NOT NULL,

    CONSTRAINT fk_registration_user FOREIGN KEY (user_id)
        REFERENCES user(id) ON DELETE CASCADE,
 
    CONSTRAINT fk_registration_courses FOREIGN KEY (course_id)
        REFERENCES courses(id) ON DELETE CASCADE,

    CONSTRAINT unique_user_courses UNIQUE (user_id, course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci ROW_FORMAT=DYNAMIC;
