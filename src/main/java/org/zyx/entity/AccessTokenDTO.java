package org.zyx.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**dto数据传输模型,和实体类相同
 * Created by SunShine on 2020/4/16.
 */
@Data
@NoArgsConstructor
public class AccessTokenDTO {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
