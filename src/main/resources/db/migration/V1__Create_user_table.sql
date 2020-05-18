CREATE TABLE USER
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(50),
    TOKEN varchar(36),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint,
    BIO varchar(50),
    AVATAR_URL varchar(100)
);