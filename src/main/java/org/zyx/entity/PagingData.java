package org.zyx.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**分页相关数据
 * Created by SunShine on 2020/4/21.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagingData {

    private List<Question> questionList;

    private Integer pageCount;
    private Integer currentPage;
    private boolean showFirstPage;
    private boolean showLast;
    private boolean showNext;
    private boolean showEndPage;
    private List<Integer> pages=new ArrayList();//页码


    public void setPagination(Integer totalCount, Integer currentPage, Integer count) {
        //总页数
        pageCount=(int)Math.ceil((double)totalCount/count);

        this.currentPage=currentPage;
        //设置页码
        for (int i=currentPage-3;i<=currentPage+3;i++){

            if(i<=0){
                i=1;
                pages.add(i);
            }else{
                pages.add(i);
            }
            if(i==pageCount || pageCount==0){
                break;
            }

        }
        //是否展示上一页
        if(currentPage == 1){
            showLast = false;
        }else{
            showLast = true;
        }

        //是否展示下一页
        if (currentPage == pageCount){
            showNext=false;
        }else{
            showNext=true;
        }

        //是否展示首页
        if(pages.contains(1)){//页码中包含第一页则不展示首页
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }

        //是否展示尾页
        if(pages.contains(pageCount)){//页码中包含第一页则不展示首页
            showEndPage = false;
        }else{
            showEndPage = true;
        }



    }
}
