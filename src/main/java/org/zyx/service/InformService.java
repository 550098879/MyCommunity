package org.zyx.service;

import org.zyx.entity.PagingData;

/**
 * Created by SunShine on 2020/5/12.
 */
public interface InformService {


    PagingData list(Long id, Integer currentPage, Integer count);
}
