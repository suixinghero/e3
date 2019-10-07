package org.e3.service;

import org.e3.common.pojo.EasyUIDataGridResult;
import org.e3.common.util.E3Result;
import org.e3.pojo.TbItem;


import java.util.Map;

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

    /**
     * 分页查询商品信息
     * @param page 页数
     * @param rows 一页有多少条数据
     * @return
     */
    public Map<String,Object> getItemList(int page, int rows);

    /**
     * 添加商品和商品描述
     * @param tbItem 商品
     * @param desc  商品描述
     * @return
     */
    public E3Result addItem(TbItem tbItem,String desc);
}
