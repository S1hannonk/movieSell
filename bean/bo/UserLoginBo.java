package movieSell.bean.bo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public class UserLoginBo {
    private String UserLoginName;
    private String UserLoginPass;

    public String getUserLoginName() {
        return UserLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        UserLoginName = userLoginName;
    }

    public String getUserLoginPass() {
        return UserLoginPass;
    }

    public void setUserLoginPass(String userLoginPass) {
        UserLoginPass = userLoginPass;
    }
}
