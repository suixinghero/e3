package org.e3.search.message;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.e3.common.pojo.SearchItem;
import org.e3.search.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**监听商品添加消息，接收消息后，将对应的商品消息同步到索引库
 * @author xujin
 * @package-name org.e3.search.message
 * @createtime 2019-10-14 16:19
 */

public class ItemAddMessageListener implements MessageListener {
    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private SolrServer solrServer;
    @Override
    public void onMessage(Message message) {
        try{
            //从消息中获取商品id
            TextMessage textMessage= (TextMessage) message;
            //根据商品id查询商品信息
            String text=textMessage.getText();
            Long itemid=new Long(text);
            //等待事务提交
            Thread.sleep(1000);
            SearchItem searchItem=itemMapper.getItemById(itemid);
            //创建文档对象
            SolrInputDocument solrInputFields=new SolrInputDocument();
            //向文档对象中添加域
            solrInputFields.addField("id",searchItem.getId());
            solrInputFields.addField("item_title",searchItem.getTitle());
            solrInputFields.addField("item_sell_point",searchItem.getSell_point());
            solrInputFields.addField("item_price",searchItem.getPrice());
            solrInputFields.addField("item_image",searchItem.getImage());
            solrInputFields.addField("item_category_name",searchItem.getCategory_name());
            //把文档对象加入索引库
            solrServer.add(solrInputFields);
            //提交
            solrServer.commit();
        }catch (Exception e){
             e.printStackTrace();
        }
    }
}
