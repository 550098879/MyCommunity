package org.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** 评论实体类
 * Created by SunShine on 2020/5/4.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long parentId;
    private String content;
    private Integer type;

}
