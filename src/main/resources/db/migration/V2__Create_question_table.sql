CREATE TABLE question
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    TITLE varchar(30),
    DISCRIPTION clob,
    GMT_CREATE bigint,
    GMT_MODIFIED bigint,
    CREATER_ID bigint,
    COMMENT_COUNT bigint DEFAULT 0,
    VIEW_COUNT bigint DEFAULT 0,
    LIKE_COUNT bigint DEFAULT 0,
    TAGS varchar(256)
);