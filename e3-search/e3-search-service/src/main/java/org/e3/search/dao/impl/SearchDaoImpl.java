package org.e3.search.dao.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.e3.common.pojo.SearchItem;
import org.e3.common.pojo.SearchResult;
import org.e3.search.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xujin
 * @package-name org.e3.search.dao.impl
 * @createtime 2019-10-12 14:57
 */
@Repository("searchDao")
public class SearchDaoImpl implements SearchDao {

    @Autowired
    private SolrServer solrServer;
    @Override
    public SearchResult search(SolrQuery solrQuery) throws SolrServerException {
        //根据query查询索引库
        QueryResponse queryResponse=solrServer.query(solrQuery);
        //取查询结果
        SolrDocumentList solrDocumentList= queryResponse.getResults();
        //查询总记录数
        SearchResult searchResult=new SearchResult();
        Long recourdCount=solrDocumentList.getNumFound();
        searchResult.setRecourdCount(recourdCount);
        List<SearchItem> searchItemList=new ArrayList<>();
        //取商品列表
        Map<String,Map<String,List<String>>> map=queryResponse.getHighlighting();
        for (SolrDocument solrDocument : solrDocumentList) {
            SearchItem searchItem=new SearchItem();
            searchItem.setId((String) solrDocument.get("id"));
            //取高亮
            List<String> stringList=map.get(solrDocument.get("id")).get("item_title");
            String title="";
            if (stringList!=null&&stringList.size()>0){
                title=stringList.get(0);
            }else{
                title= (String) solrDocument.get("item_title");
            }
            searchItem.setTitle(title);
            searchItem.setSell_point((String) solrDocument.get("item_sell_point"));
            searchItem.setPrice((Long) solrDocument.get("item_price"));
            searchItem.setImage((String) solrDocument.get("item_image"));
            searchItem.setCategory_name((String) solrDocument.get("item_category_name"));
            searchItemList.add(searchItem);
        }
        searchResult.setSearchItemList(searchItemList);
        return searchResult;
    }
}
