package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("special_hall")
public class SpecialHall {

    @TableId(value = "special_id", type = IdType.AUTO)
    private Integer specialId;
    @TableField("special_name")
    private String specialName;
    @TableField("special_sort")
    private Integer specialSort;

    public Integer getSpecialId() {
        return specialId;
    }

    public void setSpecialId(Integer specialId) {
        this.specialId = specialId;
    }

    public String getSpecialName() {
        return specialName;
    }

    public void setSpecialName(String specialName) {
        this.specialName = specialName == null ? null : specialName.trim();
    }

    public Integer getSpecialSort() {
        return specialSort;
    }

    public void setSpecialSort(Integer specialSort) {
        this.specialSort = specialSort;
    }
}