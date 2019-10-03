package org.e3.service.impl;

import org.e3.mapper.TbItemMapper;
import org.e3.pojo.TbItem;
import org.e3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * @author xujin
 * @package-name org.e3.service.impl
 * @createtime 2019-10-02 22:26
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;

    @Override
    public TbItem getItemlById(Long id) {
        TbItem tbItem=itemMapper.selectByPrimaryKey(id);
        return tbItem;
    }
}
