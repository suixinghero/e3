package org.e3.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.e3.common.util.E3Result;
import org.e3.common.util.IDUtils;
import org.e3.mapper.TbItemDescMapper;
import org.e3.mapper.TbItemMapper;
import org.e3.pojo.TbItem;
import org.e3.pojo.TbItemDesc;
import org.e3.pojo.TbItemExample;
import org.e3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**商品管理
 * @author xujin
 * @package-name org.e3.service.impl
 * @createtime 2019-10-02 22:26
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Resource(name="topicDestination")
    private Destination destination;
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

    @Override
    public E3Result addItem(TbItem tbItem, String desc) {
        //生成商品id
        final Long itemId=IDUtils.genItemId();
        //补全item属性
        tbItem.setId(itemId);
        //'商品状态，1-正常，2-下架，3-删除'
        tbItem.setStatus(((byte)1));
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        //向商品表插入数据
        int flag1=itemMapper.insert(tbItem);
        //创建一个商品描述表对应的pojo对象
        TbItemDesc tbItemDesc=new TbItemDesc();
        //补全商品描述表属性
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        //向商品描述表插入数据
        int flag2=itemDescMapper.insert(tbItemDesc);
        //发送消息
        jmsTemplate.send(destination, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage=session.createTextMessage(itemId.toString());
                return textMessage;
            }
        });
        //返回成功
        if(flag1>0&&flag2>0){
            return  E3Result.ok();
        }else{
            return E3Result.build(500,"NO OK",null);
        }

    }


}
