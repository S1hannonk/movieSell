package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:影片搜索业务模型类 继承搜索超类
 */
public class FilmSearchBo extends BaseSearchBo {

    //筛选条件
    private Integer categoryID; //影片类型编号
    private Integer regionID; //影片拍摄地编号
    private String year; //上映年份

    private String keyWord;  //搜索关键字
    //get set

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
