package org.e3.service;

import org.e3.pojo.TbItem;

/**
 * @author xujin
 * @package-name org.e3.service
 * @createtime 2019-10-02 22:21
 */

public interface ItemService {
    /**
     * 通过商品id得到商品信息
     * @param id 商品id
     * @return
     */
    public TbItem getItemlById(Long id);
}
