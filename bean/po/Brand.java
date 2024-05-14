package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("brand")
public class Brand {
    @TableId("brand_id")
    private Integer brandId;
    @TableField("brand_name")
    private String brandName;
    @TableField("brand_sort")
    private Integer brandSort;
    @TableField("brand_enable")
    private Byte brandEnable;

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName == null ? null : brandName.trim();
    }

    public Integer getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Integer brandSort) {
        this.brandSort = brandSort;
    }

    public Byte getBrandEnable() {
        return brandEnable;
    }

    public void setBrandEnable(Byte brandEnable) {
        this.brandEnable = brandEnable;
    }
}