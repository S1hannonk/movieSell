package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public class UserRegistBo {
    private String userLoginName;
    private String userLoginPass;
    private String userNickName;
    private String userSMSValidate;

    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public String getUserLoginPass() {
        return userLoginPass;
    }

    public void setUserLoginPass(String userLoginPass) {
        this.userLoginPass = userLoginPass;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserSMSValidate() {
        return userSMSValidate;
    }

    public void setUserSMSValidate(String userSMSValidate) {
        this.userSMSValidate = userSMSValidate;
    }
}
