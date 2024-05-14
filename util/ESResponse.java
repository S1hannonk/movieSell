package movieSell.util;

import java.util.List;

public class ESResponse<T> {

    private List<T> data;       // 实体模型对象集合
    private long resultCount;   // 总命中数

    // getters and setters
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getResultCount() {
        return resultCount;
    }

    public void setResultCount(long resultCount) {
        this.resultCount = resultCount;
    }
}