package org.zyx.service;

import org.zyx.entity.CommentDTO;
import org.zyx.entity.CommentData;
import org.zyx.model.User;

import java.util.Collection;

/**
 * Created by SunShine on 2020/5/4.
 */
public interface CommentService {
    void insertComment(CommentDTO commentDTO, User user);

    /**
     * 根基类型查找评论
     * @param type 1为父评论,2为子评论
     * @return
     */
    Collection<CommentData> findComment(long parentId, int type);

    Long incLikeCount(long commentId);
}
