package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.util.Date;

@TableName("film")
public class Film {
    @TableId("film_id")
    private Integer filmId;
    @TableField("film_real_name")
    private String filmRealName;
    @TableField("film_name")
    private String filmName;
    @TableField("film_desc")
    private String filmDesc;
    @TableField("film_avat")
    private String filmAvat;
    @TableField("film_imgs")
    private String filmImgs;
    @TableField("film_show_time")
    private Date filmShowTime;
    @TableField("film_show_place")
    private String filmShowPlace;
    @TableField("film_timelong")
    private String filmTimelong;
    @TableField("film_end_time")
    private Byte filmEndTime;
    @TableField("film_favorite")
    private Integer filmFavorite;
    @TableField("film_totalcost")
    private Integer filmTotalcost;
    @TableField("film_praise")
    private BigDecimal filmPraise;
    @TableField("film_praise_star")
    private Byte filmPraiseStar;
    @TableField("film_praise_pepole")
    private String filmPraisePepole;
    @TableField("film_advance")
    private String filmAdvance;
    @TableField("film_attribute")
    private String filmAttribute;
    @TableField("film_cate_keyword")
    private String filmCateKeyword;
    @TableField("film_region_keyword")
    private String filmRegionKeyword;
    @TableField("film_years")
    private String filmYears;
    @TableField("film_cate_query")
    private String filmCateQuery;
    @TableField("film_enabled")
    private Byte filmEnabled;
    @TableField("film_region_query")
    private String filmRegionQuery;
    @TableField("film_actors")
    private String filmActors;
    @TableField("film_awards")
    private String filmAwards;

    public String getFilmActors() {
        return filmActors;
    }

    public void setFilmActors(String filmActors) {
        this.filmActors = filmActors == null ? null : filmActors.trim();
    }

    public String getFilmAwards() {
        return filmAwards;
    }

    public void setFilmAwards(String filmAwards) {
        this.filmAwards = filmAwards == null ? null : filmAwards.trim();
    }

    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getFilmRealName() {
        return filmRealName;
    }

    public void setFilmRealName(String filmRealName) {
        this.filmRealName = filmRealName == null ? null : filmRealName.trim();
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName == null ? null : filmName.trim();
    }

    public String getFilmDesc() {
        return filmDesc;
    }

    public void setFilmDesc(String filmDesc) {
        this.filmDesc = filmDesc == null ? null : filmDesc.trim();
    }

    public String getFilmAvat() {
        return filmAvat;
    }

    public void setFilmAvat(String filmAvat) {
        this.filmAvat = filmAvat == null ? null : filmAvat.trim();
    }

    public String getFilmImgs() {
        return filmImgs;
    }

    public void setFilmImgs(String filmImgs) {
        this.filmImgs = filmImgs == null ? null : filmImgs.trim();
    }

    public Date getFilmShowTime() {
        return filmShowTime;
    }

    public void setFilmShowTime(Date filmShowTime) {
        this.filmShowTime = filmShowTime;
    }

    public String getFilmShowPlace() {
        return filmShowPlace;
    }

    public void setFilmShowPlace(String filmShowPlace) {
        this.filmShowPlace = filmShowPlace == null ? null : filmShowPlace.trim();
    }

    public String getFilmTimelong() {
        return filmTimelong;
    }

    public void setFilmTimelong(String filmTimelong) {
        this.filmTimelong = filmTimelong == null ? null : filmTimelong.trim();
    }

    public Byte getFilmEndTime() {
        return filmEndTime;
    }

    public void setFilmEndTime(Byte filmEndTime) {
        this.filmEndTime = filmEndTime;
    }

    public Integer getFilmFavorite() {
        return filmFavorite;
    }

    public void setFilmFavorite(Integer filmFavorite) {
        this.filmFavorite = filmFavorite;
    }

    public Integer getFilmTotalcost() {
        return filmTotalcost;
    }

    public void setFilmTotalcost(Integer filmTotalcost) {
        this.filmTotalcost = filmTotalcost;
    }

    public BigDecimal getFilmPraise() {
        return filmPraise;
    }

    public void setFilmPraise(BigDecimal filmPraise) {
        this.filmPraise = filmPraise;
    }

    public Byte getFilmPraiseStar() {
        return filmPraiseStar;
    }

    public void setFilmPraiseStar(Byte filmPraiseStar) {
        this.filmPraiseStar = filmPraiseStar;
    }

    public String getFilmPraisePepole() {
        return filmPraisePepole;
    }

    public void setFilmPraisePepole(String filmPraisePepole) {
        this.filmPraisePepole = filmPraisePepole == null ? null : filmPraisePepole.trim();
    }

    public String getFilmAdvance() {
        return filmAdvance;
    }

    public void setFilmAdvance(String filmAdvance) {
        this.filmAdvance = filmAdvance == null ? null : filmAdvance.trim();
    }

    public String getFilmAttribute() {
        return filmAttribute;
    }

    public void setFilmAttribute(String filmAttribute) {
        this.filmAttribute = filmAttribute == null ? null : filmAttribute.trim();
    }

    public String getFilmCateKeyword() {
        return filmCateKeyword;
    }

    public void setFilmCateKeyword(String filmCateKeyword) {
        this.filmCateKeyword = filmCateKeyword == null ? null : filmCateKeyword.trim();
    }

    public String getFilmRegionKeyword() {
        return filmRegionKeyword;
    }

    public void setFilmRegionKeyword(String filmRegionKeyword) {
        this.filmRegionKeyword = filmRegionKeyword == null ? null : filmRegionKeyword.trim();
    }

    public String getFilmYears() {
        return filmYears;
    }

    public void setFilmYears(String filmYears) {
        this.filmYears = filmYears == null ? null : filmYears.trim();
    }

    public String getFilmCateQuery() {
        return filmCateQuery;
    }

    public void setFilmCateQuery(String filmCateQuery) {
        this.filmCateQuery = filmCateQuery == null ? null : filmCateQuery.trim();
    }

    public Byte getFilmEnabled() {
        return filmEnabled;
    }

    public void setFilmEnabled(Byte filmEnabled) {
        this.filmEnabled = filmEnabled;
    }

    public String getFilmRegionQuery() {
        return filmRegionQuery;
    }

    public void setFilmRegionQuery(String filmRegionQuery) {
        this.filmRegionQuery = filmRegionQuery == null ? null : filmRegionQuery.trim();
    }
}