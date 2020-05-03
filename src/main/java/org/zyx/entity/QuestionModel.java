package org.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.zyx.model.Question;
import org.zyx.model.User;

/**
 * Created by SunShine on 2020/4/19.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel extends Question{

    private User user;

}
