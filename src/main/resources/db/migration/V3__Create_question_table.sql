CREATE TABLE question
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(50),
    description TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creator INT,
    comment_count INT DEFAULT 0,
    view_count INT DEFAULT 0,
    like_count INT DEFAULT 0,
    tag VARCHAR(256)
);