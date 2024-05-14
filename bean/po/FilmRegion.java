package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("film_region")
public class FilmRegion {
    @TableId("film_re_id")
    private Integer filmReId;
    @TableField("film_re_name")
    private String filmReName;
    @TableField("film_re_enable")
    private Byte filmReEnable;

    public Integer getFilmReId() {
        return filmReId;
    }

    public void setFilmReId(Integer filmReId) {
        this.filmReId = filmReId;
    }

    public String getFilmReName() {
        return filmReName;
    }

    public void setFilmReName(String filmReName) {
        this.filmReName = filmReName == null ? null : filmReName.trim();
    }

    public Byte getFilmReEnable() {
        return filmReEnable;
    }

    public void setFilmReEnable(Byte filmReEnable) {
        this.filmReEnable = filmReEnable;
    }
}