package org.e3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.e3.mapper.TbItemMapper;
import org.e3.pojo.TbItem;
import org.e3.pojo.TbItemExample;
import org.e3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Override
    public Map<String,Object> getItemList(int page, int rows) {
        Map<String,Object> map=new HashMap<>();
        TbItemExample tbItemExample=new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItemList=itemMapper.selectByExample(tbItemExample);
        PageInfo pageInfo=new PageInfo(tbItemList);
        map.put("total",pageInfo.getTotal());
        map.put("rows",tbItemList);
        return map;
    }


}
