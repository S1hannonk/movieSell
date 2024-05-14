package movieSell.service;

import movieSell.bean.bo.OrderAddBo;

/**
 * projectName:
 *
 * @author: Shannon
 * description:
 */
public interface OrderService {
    /**
     *
     * @param orderAddBo
     * @param authorization
     * @return 验证订单是否可以添加
     */
    boolean add(OrderAddBo orderAddBo,String authorization);
}
