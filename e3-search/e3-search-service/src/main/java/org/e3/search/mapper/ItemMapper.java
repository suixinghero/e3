package org.e3.search.mapper;

import org.e3.common.pojo.SearchItem;

import java.util.List;

/**
 * @author xujin
 * @package-name org.e3.search.mapper
 * @createtime 2019-10-11 19:39
 */

public interface ItemMapper {
    public List<SearchItem> getSearchItemList();

    /**
     * 通过商品id查询商品并得到它的查询信息
     * @param itemid
     * @return
     */
    public SearchItem getItemById(Long itemid);
}
