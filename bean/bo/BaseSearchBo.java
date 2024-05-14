package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:整个项目的搜索业务模型超类
 */
public class BaseSearchBo {
    //分页条件
    private Integer page = 1; //当前页码  =requestBody
    private Integer pageSize = 10;//每页显示记录数 == requestBody
    private Integer startIndex = 0; //起始记录数 ==> 根据page 和 pagesize计算
    private Integer resultCount = 0;//总记录数  ==> 从数据源查询得到
    private Integer pageCount = 0; //总页数 ==> 根据总记录数与页数计算总页数

    //排序条件
    private String orderColumn; // 排序列
    private String orderType;    // 排序方式asc 升序 desc 降序

    //

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
        //根据page 和 pagesize计算
        setStartIndex((this.page - 1) * this.pageSize);
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
        //根据总记录数与页数计算总页数
        setPageCount(
                this.resultCount % this.pageSize == 0 ?
                        this.resultCount / this.pageSize :
                        this.resultCount / this.pageSize + 1);
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
        //控制page 的取值范围
        //先控制最大值再控制最小值
        if(this.page > this.pageCount){
            setPage(this.pageCount);
        }
        if(this.page < 1){
            setPage(1);
        }
    }

    //排序

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
