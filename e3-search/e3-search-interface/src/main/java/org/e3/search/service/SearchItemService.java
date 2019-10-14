package org.e3.search.service;

import org.e3.common.util.E3Result;

/**
 * @author xujin
 * @package-name org.e3.search.service
 * @createtime 2019-10-11 20:03
 */

public interface SearchItemService {
    /**
     * 向索引库中添加商品
     * @return
     */
    public E3Result importAllItem();
}
