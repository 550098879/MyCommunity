package org.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zyx.model.Comment;
import org.zyx.model.User;

/**
 * Created by SunShine on 2020/5/7.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentData{
    private Comment comment;
    private User user;
}
