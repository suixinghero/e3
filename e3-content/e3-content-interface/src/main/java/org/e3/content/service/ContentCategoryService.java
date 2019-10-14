package org.e3.content.service;

import org.e3.common.pojo.EasyUITreeNode;
import org.e3.common.util.E3Result;

import java.util.List;

/**
 * @author xujin
 * @package-name org.e3.content.service
 * @createtime 2019-10-08 10:18
 */

public interface ContentCategoryService {
    /**
     * 得到内容类别列表
     * @param parentId
     * @return
     */
    public List<EasyUITreeNode> getContentCatList(Long parentId);

    /**
     * 添加内容类别
     * @param parentId
     * @param name
     * @return
     */
    public E3Result addContentCategory(Long parentId,String name);

    /**
     * 删除内容类别
     * @param id
     * @return
     */
    public E3Result deleteContentCategory(Long id);

    /**
     * 更新内容节点
     * @param id
     * @param name
     * @return
     */
    public E3Result updateContentCategory(Long id,String name);
}
