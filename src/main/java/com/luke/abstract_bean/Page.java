package com.luke.abstract_bean;

import java.util.List;

/***
 * com.luke.abstract_bean
 * dllo
 * 18/7/19
 *             ,%%%%%%%%,
 *           ,%%/\%%%%/\%%
 *          ,%%%\c "" J/%%%
 * %.       %%%%/ 0  0 \%%%
 * `%%.     %%%%    _  |%%%
 *  `%%     `%%%%(__Y__)%%'
 *  //       ;%%%%`\-/%%%'
 * ((       /  `%%%%%%%'
 *  \\    .'     '%%%'|    攻
 *   \\  /       \  | |    城
 *    \\/         ) | |    湿
 *     \         /_ | |__
 *     (___________))))))) 
 *
 *       我湿一吼  BUG无有                        
 */
public class Page<T> {
    private int total;//数据条目总数量
    private int currentPage = 1;//当前页码
    private int singlePageCount = 5;//单页显示数量
    private int totalPageSize;//总页数
    private int beginNum;//limit 起始位置
    private List<T> list;//要显示的数据

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "total=" + total +
                ", currentPage=" + currentPage +
                ", singlePageCount=" + singlePageCount +
                ", totalPageSize=" + totalPageSize +
                ", beginNum=" + beginNum +
                ", list=" + list +
                '}';
    }

    public int getBeginNum() {
        beginNum = (currentPage-1)*singlePageCount;
        return beginNum;
    }

    public void setBeginNum(int beginNum) {
        this.beginNum = beginNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSinglePageCount() {
        return singlePageCount;
    }

    public void setSinglePageCount(int singlePageCount) {
        this.singlePageCount = singlePageCount;
    }

    public int getTotalPageSize() {
        /**
         * 页面调用totalPageSize时,是通过get方法调用的
         * 调用该方法是,进行计算得到totalPageSize
         */
        if (total % singlePageCount == 0){
            totalPageSize = total/singlePageCount;
        }else {
            totalPageSize = total/singlePageCount + 1;
        }
        return totalPageSize;
    }

    public void setTotalPageSize(int totalPageSize) {
        this.totalPageSize = totalPageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
