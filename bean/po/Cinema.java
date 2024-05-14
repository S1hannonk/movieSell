package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("cinema")
public class Cinema {
    @TableId("cma_id")
    private Integer cmaId;
    @TableField("cma_name")
    private String cmaName;
    @TableField("cma_address")
    private String cmaAddress;
    @TableField("cma_phone")
    private String cmaPhone;
    @TableField("cma_sepcial")
    private String cmaSepcial;
    @TableField("cma_service")
    private String cmaService;
    @TableField("cma_imgs")
    private String cmaImgs;
    @TableField("cma_cost")
    private Integer cmaCost;
    @TableField("cma_away")
    private String cmaAway;
    @TableField("cma_sort")
    private Byte cmaSort;
    @TableField("cma_url")
    private String cmaUrl;
    @TableField("cma_service_query")
    private String cmaServiceQuery;
    @TableField("cma_halls_query")
    private String cmaHallsQuery;
    @TableField("cma_relation")
    private String cmaRelation;
    @TableField("brand_id")
    private Integer brandId;
    @TableField("cma_enable")
    private Byte cmaEnable;

    public Integer getCmaId() {
        return cmaId;
    }

    public void setCmaId(Integer cmaId) {
        this.cmaId = cmaId;
    }

    public String getCmaName() {
        return cmaName;
    }

    public void setCmaName(String cmaName) {
        this.cmaName = cmaName == null ? null : cmaName.trim();
    }

    public String getCmaAddress() {
        return cmaAddress;
    }

    public void setCmaAddress(String cmaAddress) {
        this.cmaAddress = cmaAddress == null ? null : cmaAddress.trim();
    }

    public String getCmaPhone() {
        return cmaPhone;
    }

    public void setCmaPhone(String cmaPhone) {
        this.cmaPhone = cmaPhone == null ? null : cmaPhone.trim();
    }

    public String getCmaSepcial() {
        return cmaSepcial;
    }

    public void setCmaSepcial(String cmaSepcial) {
        this.cmaSepcial = cmaSepcial == null ? null : cmaSepcial.trim();
    }

    public String getCmaService() {
        return cmaService;
    }

    public void setCmaService(String cmaService) {
        this.cmaService = cmaService == null ? null : cmaService.trim();
    }

    public String getCmaImgs() {
        return cmaImgs;
    }

    public void setCmaImgs(String cmaImgs) {
        this.cmaImgs = cmaImgs == null ? null : cmaImgs.trim();
    }

    public Integer getCmaCost() {
        return cmaCost;
    }

    public void setCmaCost(Integer cmaCost) {
        this.cmaCost = cmaCost;
    }

    public String getCmaAway() {
        return cmaAway;
    }

    public void setCmaAway(String cmaAway) {
        this.cmaAway = cmaAway == null ? null : cmaAway.trim();
    }

    public Byte getCmaSort() {
        return cmaSort;
    }

    public void setCmaSort(Byte cmaSort) {
        this.cmaSort = cmaSort;
    }

    public String getCmaUrl() {
        return cmaUrl;
    }

    public void setCmaUrl(String cmaUrl) {
        this.cmaUrl = cmaUrl == null ? null : cmaUrl.trim();
    }

    public String getCmaServiceQuery() {
        return cmaServiceQuery;
    }

    public void setCmaServiceQuery(String cmaServiceQuery) {
        this.cmaServiceQuery = cmaServiceQuery == null ? null : cmaServiceQuery.trim();
    }

    public String getCmaHallsQuery() {
        return cmaHallsQuery;
    }

    public void setCmaHallsQuery(String cmaHallsQuery) {
        this.cmaHallsQuery = cmaHallsQuery == null ? null : cmaHallsQuery.trim();
    }

    public String getCmaRelation() {
        return cmaRelation;
    }

    public void setCmaRelation(String cmaRelation) {
        this.cmaRelation = cmaRelation == null ? null : cmaRelation.trim();
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public Byte getCmaEnable() {
        return cmaEnable;
    }

    public void setCmaEnable(Byte cmaEnable) {
        this.cmaEnable = cmaEnable;
    }
}