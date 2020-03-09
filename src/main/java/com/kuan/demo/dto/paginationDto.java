package com.kuan.demo.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class paginationDto {
    private List<QuestionDto> questions;
    private Integer page;
    private Boolean pagePre;
    private Boolean pageNext;
    private Boolean firstPage;
    private Boolean finalPage;
    private List<Integer> pages =new ArrayList<>();


    public void autoPageProfile(Integer questionCount, Integer page, Integer size) {
        Integer pageCount;
        //计算分页总数
        if (questionCount % size == 0) {
            pageCount=questionCount/size;
        }else {
            pageCount=questionCount/size+1;
        }
        pages.add(page);
        for( int i=1 ;i<=3;i++){
            if((page-i)>0){
                pages.add(0,page-i);
            }
            if((page + i) <= pageCount){
                pages.add(page+i);
            }
        }



        //是否显示第一页
        if(pages.contains(1)){
            firstPage = false;
        }else {
            firstPage = true;
        }

        //是否显示最后一页
        if(pages.contains(pageCount)){
            finalPage = false;
        }else {
            finalPage = true;
        }

        //是否有上一项
        if(page == 1){
            pagePre = false;
        }else{
            pagePre = true;
        }

        //是否有下一项
        if(page == pageCount ){
            pageNext = false;
        }else{
            pageNext = true;
        }
    }
}
