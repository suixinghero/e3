package org.e3.search.service;

import org.e3.common.pojo.SearchResult;

/**商品搜索service
 * @author xujin
 * @package-name org.e3.search.service
 * @createtime 2019-10-12 15:35
 */

public interface SearchService {
    /**
     * 商品搜索
     * @param keyword 搜索条件
     * @param page  页面
     * @param rows  一页的商品数
     * @return
     */
    public SearchResult search(String keyword,Integer page,Integer rows) throws Exception;
}
