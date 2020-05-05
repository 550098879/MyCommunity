package org.zyx.service;

import org.zyx.entity.CommentDTO;
import org.zyx.model.Comment;

/**
 * Created by SunShine on 2020/5/4.
 */
public interface CommentService {
    void insertComment(CommentDTO commentDTO,Long userId);
}
