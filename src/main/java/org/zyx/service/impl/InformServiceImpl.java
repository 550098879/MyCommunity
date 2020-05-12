package org.zyx.service.impl;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zyx.entity.PagingData;
import org.zyx.model.Inform;
import org.zyx.model.InformExample;
import org.zyx.repository.InformMapper;
import org.zyx.repository.UserMapper;
import org.zyx.service.InformService;

import java.util.List;

/**
 * Created by SunShine on 2020/5/12.
 */
@Service
public class InformServiceImpl implements InformService{

    @Autowired
    private InformMapper informMapper;
    @Override
    public PagingData list(Long id, Integer currentPage, Integer count) {

        PagingData<Inform> pagingData = new PagingData<>();


//        获取数量
        InformExample example = new InformExample();
        example.createCriteria().andReceiverEqualTo(id);
        Integer totalCount=(int)informMapper.countByExample(example);
        pagingData.setPagination(totalCount,currentPage,count);//分页设置
//        获取回复列表
        example.setOrderByClause("gmt_create desc");
        List<Inform> informs = informMapper
                .selectByExampleWithRowbounds(example,new RowBounds((currentPage-1)*count,count));

        if(informs.size()==0){
            return pagingData;
        }

        /**1.从流中取出发送通知的用户的id集合
         *
         List<Long> userIdList = informs.stream().distinct().map(inform -> inform.getNotifier())
         .collect(Collectors.toList());

         //        Set<Long> disUserIds = informs.stream().map(inform -> inform.getNotifier())
         //                .collect(Collectors.toSet());//去重?

         System.out.println(userIdList);

         UserExample userExample= new UserExample();
         userExample.createCriteria().andIdIn(userIdList);
         List<User> userList = userMapper.selectByExample(userExample);

         Map<Long, User> userMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), u -> u));

         //        将回复中的信息转到InformDTO中
         List<Inform> informDTOList = new ArrayList<>();
         *
         */

        pagingData.setData(informs);

        return pagingData;
    }
}
