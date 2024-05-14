package movieSell.bean.po;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("category")
public class Category {
    @TableId("cate_id")
    private Integer cateId;
    @TableField("cate_name")
    private String cateName;
    @TableField("cate_enable")
    private Byte cateEnable;

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName == null ? null : cateName.trim();
    }

    public Byte getCateEnable() {
        return cateEnable;
    }

    public void setCateEnable(Byte cateEnable) {
        this.cateEnable = cateEnable;
    }
}