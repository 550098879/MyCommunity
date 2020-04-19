CREATE TABLE question
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30),
    discription TEXT,
    gmt_create BIGINT,
    gmt_modified BIGINT,
    creater_id BIGINT,
    comment_count BIGINT DEFAULT 0,
    view_dount BIGINT DEFAULT 0,
    like_count BIGINT DEFAULT 0,
    tags VARCHAR(256),
    foreign key(id) references USER(id)  on update cascade
);