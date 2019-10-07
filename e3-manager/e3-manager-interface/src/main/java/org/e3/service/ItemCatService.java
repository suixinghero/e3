package org.e3.service;

import org.e3.common.pojo.EasyUITreeNode;

import java.util.List;

/**
 * @author xujin
 * @package-name org.e3.service
 * @createtime 2019-10-05 11:48
 */

public interface ItemCatService {
    /**
     * 得到商品类别列表
     * @param patentId
     * @return
     */
    public List<EasyUITreeNode> getItemCatList(Long patentId);
}
