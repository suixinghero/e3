package org.e3.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * @author xujin
 * @package-name org.e3.common.pojo
 * @createtime 2019-10-12 14:51
 */

public class SearchResult implements Serializable {
    private Long recourdCount;
    private Long totalPages;
    private List<SearchItem> searchItemList;

    public Long getRecourdCount() {
        return recourdCount;
    }

    public void setRecourdCount(Long recourdCount) {
        this.recourdCount = recourdCount;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    public List<SearchItem> getSearchItemList() {
        return searchItemList;
    }

    public void setSearchItemList(List<SearchItem> searchItemList) {
        this.searchItemList = searchItemList;
    }
}
