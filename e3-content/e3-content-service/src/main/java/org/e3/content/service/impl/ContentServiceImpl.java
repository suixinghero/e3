package org.e3.content.service.impl;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.e3.common.jedis.JedisClient;
import org.e3.common.util.E3Result;
import org.e3.common.util.JsonUtils;
import org.e3.content.service.ContentService;
import org.e3.mapper.TbContentMapper;
import org.e3.pojo.TbContent;
import org.e3.pojo.TbContentExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**内容管理service
 * @author xujin
 * @package-name org.e3.content.service.impl
 * @createtime 2019-10-09 16:50
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
    private JedisClient jedisClient;

    @Value("${tbContent_list}")
    private String tbContent_list;
    /**
     * 得到内容列表
     * @param categoryId 内容分类id
     * @return
     */
    @Override
    public Map<String,Object> getContentList(Long categoryId, int page, int rows) {
        Map<String,Object> map=new HashMap<>();
        TbContentExample tbContentExample=new TbContentExample();
        TbContentExample.Criteria criteria=tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList=tbContentMapper.selectByExample(tbContentExample);
        PageInfo pageInfo=new PageInfo(tbContentList);
        map.put("total",pageInfo.getTotal());
        map.put("rows",tbContentList);
        return map;
    }

    @Override
    public E3Result addContent(TbContent tbContent) {
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        tbContentMapper.insert(tbContent);
        //缓存同步
        try{
            jedisClient.hdel(tbContent_list,tbContent.getCategoryId()+"");
        }catch (Exception e){
            e.printStackTrace();
        }
        return E3Result.ok();
    }

    @Override
    public List<TbContent> getContentListByCategoryId(Long categoryId) {
        //查询缓存
        try {
            //如果缓存中没有则直接相应结果
            String json=jedisClient.hget(tbContent_list,categoryId+"");
            if(StringUtils.isNoneBlank(json)) {
                List<TbContent> tbContentList = JsonUtils.jsonToList(json, TbContent.class);
                return  tbContentList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //如果没有查询数据库
        TbContentExample tbContentExample=new TbContentExample();
        TbContentExample.Criteria criteria=tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContentList=tbContentMapper.selectByExampleWithBLOBs(tbContentExample);
        //把查询结果添加到缓存中
        try {
            jedisClient.hset(tbContent_list,categoryId+"",JsonUtils.objectToJson(tbContentList));
        }catch (Exception e){
            e.printStackTrace();
        }
        return  tbContentList;
    }
}
