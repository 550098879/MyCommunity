package org.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SunShine on 2020/4/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private Long id;
    private String title;
    private String discription;
    private Long gmt_create;
    private Long gmt_modified;
    private Long creater_id;
    private Long comment_count;
    private Long view_count;
    private Long like_count;
    private String tags;

    private User user;

}
