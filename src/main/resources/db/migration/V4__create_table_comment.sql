CREATE TABLE PUBLIC.comment
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    parent_id BIGINT NOT NULL,   /*父类ID*/
    type INT NOT NULL,           /*父类类型*/
    comment_id BIGINT NOT NULL,     /*评论人ID*/
    gmt_create BIGINT NOT NULL,  /*评论时间*/
    gmt_modified BIGINT NOT NULL,/*更新时间*/
    like_count BIGINT DEFAULT 0,  /*点赞数*/
    content varchar(1024)   /*评论内容*/
);
