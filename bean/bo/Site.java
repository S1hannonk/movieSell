package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:座位模型类
 */
public class Site {
    private String site_no;//座位编号
    private Integer site_row; //座位行号
    private Integer site_colum;//座位列号
    private Integer site_state;//座位状态

    //get set 方法
    public String getSite_no() {
        return site_no;
    }

    public void setSite_no(String site_no) {
        this.site_no = site_no;
    }

    public Integer getSite_row() {
        return site_row;
    }

    public void setSite_row(Integer site_row) {
        this.site_row = site_row;
    }

    public Integer getSite_colum() {
        return site_colum;
    }

    public void setSite_colum(Integer site_colum) {
        this.site_colum = site_colum;
    }

    public Integer getSite_state() {
        return site_state;
    }

    public void setSite_state(Integer site_state) {
        this.site_state = site_state;
    }
}
