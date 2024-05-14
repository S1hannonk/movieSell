package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("cinema_film_rel")
public class CinemaFilmRel {
    @TableId("cma_id")
    private Integer id;
    @TableField("cma_id")
    private Integer cmaId;
    @TableField("film_id")
    private Integer filmId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCmaId() {
        return cmaId;
    }

    public void setCmaId(Integer cmaId) {
        this.cmaId = cmaId;
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
}