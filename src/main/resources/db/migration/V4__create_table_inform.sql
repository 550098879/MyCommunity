CREATE TABLE INFORM
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY,
    NOTIFIER bigint NOT NULL,
    RECEIVER bigint NOT NULL,
    OUTERID bigint NOT NULL,
    TYPE integer NOT NULL,
    GMT_CREATE bigint NOT NULL,
    STATUS integer DEFAULT 0 NOT NULL,
    INFORM_NAME varchar(100) NOT NULL,
    OUTER_TITLE varchar(200)
);
