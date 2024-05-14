package movieSell.bean.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("user_info")
public class UserInfo {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;
    @TableField("user_login_name")
    private String userLoginName;
    @TableField("user_login_pass")
    private String userLoginPass;
    @TableField("user_nick_name")
    private String userNickName;
    @TableField("user_phone")
    private String userPhone;
    @TableField("user_email")
    private String userEmail;
    @TableField("user_enable")
    private Byte userEnable;

    // getters and setters
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName == null ? null : userLoginName.trim();
    }

    public String getUserLoginPass() {
        return userLoginPass;
    }

    public void setUserLoginPass(String userLoginPass) {
        this.userLoginPass = userLoginPass == null ? null : userLoginPass.trim();
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName == null ? null : userNickName.trim();
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public Byte getUserEnable() {
        return userEnable;
    }

    public void setUserEnable(Byte userEnable) {
        this.userEnable = userEnable;
    }
}