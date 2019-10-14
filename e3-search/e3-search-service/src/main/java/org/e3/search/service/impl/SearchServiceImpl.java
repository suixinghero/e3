package org.e3.search.service.impl;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.e3.common.pojo.SearchResult;
import org.e3.search.dao.SearchDao;
import org.e3.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujin
 * @package-name org.e3.search.service.impl
 * @createtime 2019-10-12 15:37
 */
@Service("searchService")
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResult search(String keyword, Integer page, Integer rows) throws SolrServerException {
        //创建一个solrquery对象
        SolrQuery solrQuery=new SolrQuery();
        //设置搜索条件
        solrQuery.setQuery(keyword);
        //设置分页条件
        if(page<=0) page=1;
        solrQuery.setStart((page-1)*rows);
        solrQuery.setRows(rows);
        //设置默认的搜索域
        solrQuery.set("df","item_title");
        //开启高亮
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("item_title");
        solrQuery.setHighlightSimplePre("<em style='color:red;'>");
        solrQuery.setHighlightSimplePost("</em>");
        //调用dao执行查询
        SearchResult searchResult=searchDao.search(solrQuery);
        //计算总页数
        Long totalPages=(searchResult.getRecourdCount()%rows)==0?(searchResult.getRecourdCount()/rows):(searchResult.getRecourdCount()/rows+1);
        searchResult.setTotalPages(totalPages);
        //返回结果
        return searchResult;
    }
}
