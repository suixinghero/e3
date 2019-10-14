package org.e3.search.service.impl;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.e3.common.pojo.SearchItem;
import org.e3.common.util.E3Result;
import org.e3.search.mapper.ItemMapper;
import org.e3.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**索引库维护service
 * @author xujin
 * @package-name org.e3.search.service.impl
 * @createtime 2019-10-11 20:04
 */
@Service("searchItemService")
public class SearchItemServiceImpl implements SearchItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private SolrServer solrServer;

    @Override
    public E3Result importAllItem() {
        try {
            //查询商品列表
            List<SearchItem> searchItemList=itemMapper.getSearchItemList();
            //遍历商品列表
            for (SearchItem searchItem : searchItemList) {
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
            }
            //提交
            solrServer.commit();
            //返回导入成功
            return  E3Result.ok();
        }catch (Exception e){
            e.printStackTrace();
            return  E3Result.build(500,"数据导入发生异常");
        }
    }
}
