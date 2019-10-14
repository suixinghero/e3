package org.e3.content.service;

import org.e3.common.util.E3Result;
import org.e3.pojo.TbContent;

import java.util.List;
import java.util.Map;

/**
 * @author xujin
 * @package-name org.e3.content.service
 * @createtime 2019-10-09 16:49
 */

public interface ContentService {
    /**
     *得到内容
     * @param categoryId
     * @return
     */
    public Map<String,Object> getContentList(Long categoryId, int page, int rows);

    /**
     * 添加内容
     * @param tbContent
     * @return
     */
    public E3Result addContent(TbContent tbContent);

    /**
     * 通过内容分类id得到内容列表
     * @param categoryId
     * @return
     */
    public List<TbContent> getContentListByCategoryId(Long categoryId);
}
