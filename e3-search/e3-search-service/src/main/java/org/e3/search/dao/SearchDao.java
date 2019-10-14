package org.e3.search.dao;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.e3.common.pojo.SearchResult;

/**商品搜索dao
 * @author xujin
 * @package-name org.e3.search.dao
 * @createtime 2019-10-12 14:57
 */

public interface SearchDao {
    /**
     * 根据查询条件查询索引库
     * @param solrQuery
     * @return
     */
    public SearchResult search(SolrQuery solrQuery) throws SolrServerException;
}
