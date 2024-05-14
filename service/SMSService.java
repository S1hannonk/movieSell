package movieSell.service;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface SMSService {
    /**
     *
     * @param phone
     * @return 是否发送成功
     */
    boolean sendSMSValidate( String phone);
}
