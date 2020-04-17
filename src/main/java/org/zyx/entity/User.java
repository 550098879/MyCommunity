package org.zyx.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by SunShine on 2020/4/17.
 */
@Data
@NoArgsConstructor
public class User {

    private Long id;
    private String account_id;
    private String name;
    private String token;
    private Long gmt_create;//创建时间
    private Long gmt_modified;//改动时间
    private String bio;//简介


}
