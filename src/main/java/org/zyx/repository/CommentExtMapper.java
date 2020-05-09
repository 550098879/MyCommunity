package org.zyx.repository;

public interface CommentExtMapper {

    void incCommentCount(long parentId);

    void incLikeCount(long commentId);



}