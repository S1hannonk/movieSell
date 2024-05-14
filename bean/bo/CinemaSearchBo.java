package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:搜索业务超类
 */
public class CinemaSearchBo extends BaseSearchBo{
    private Integer BrandId;   // 影院品牌编号
    private Integer specialHallId ; // 放映厅类型编号
    private Integer serviceId; //影院服务id
    private String keyWord; //搜索关键字

    public Integer getBrandId() {
        return BrandId;
    }

    public void setBrandId(Integer cinemaId) {
        this.BrandId = cinemaId;
    }

    public Integer getSpecialHallId() {
        return specialHallId;
    }

    public void setSpecialHallId(Integer specialHallId) {
        this.specialHallId = specialHallId;
    }

    public Integer getServiceId() {
        return serviceId;
    }

    public void setServiceId(Integer serviceId) {
        this.serviceId = serviceId;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
