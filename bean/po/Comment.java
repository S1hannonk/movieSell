package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("comment")
public class Comment {
    @TableId(value = "cmmt_id", type = IdType.AUTO)
    private Integer cmmtId;
    @TableField("cmmt_desc")
    private String cmmtDesc;
    @TableField("cmmt_score")
    private Byte cmmtScore;
    @TableField("cmmt_createtime")
    private String cmmtCreatetime;
    @TableField("cmmt_good")
    private Integer cmmtGood;
    @TableField("cust_id")
    private Integer custId;
    @TableField("cust_name")
    private String custName;
    @TableField("cust_avat")
    private String custAvat;
    @TableField("film_id")
    private Integer filmId;

    public Integer getCmmtId() {
        return cmmtId;
    }

    public void setCmmtId(Integer cmmtId) {
        this.cmmtId = cmmtId;
    }

    public String getCmmtDesc() {
        return cmmtDesc;
    }

    public void setCmmtDesc(String cmmtDesc) {
        this.cmmtDesc = cmmtDesc == null ? null : cmmtDesc.trim();
    }

    public Byte getCmmtScore() {
        return cmmtScore;
    }

    public void setCmmtScore(Byte cmmtScore) {
        this.cmmtScore = cmmtScore;
    }

    public String getCmmtCreatetime() {
        return cmmtCreatetime;
    }

    public void setCmmtCreatetime(String cmmtCreatetime) {
        this.cmmtCreatetime = cmmtCreatetime == null ? null : cmmtCreatetime.trim();
    }

    public Integer getCmmtGood() {
        return cmmtGood;
    }

    public void setCmmtGood(Integer cmmtGood) {
        this.cmmtGood = cmmtGood;
    }

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName == null ? null : custName.trim();
    }

    public String getCustAvat() {
        return custAvat;
    }

    public void setCustAvat(String custAvat) {
        this.custAvat = custAvat == null ? null : custAvat.trim();
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
}