package org.e3.controller;

import org.e3.common.util.E3Result;
import org.e3.content.service.ContentService;
import org.e3.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**内容管理的controller
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-09 16:59
 */
@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/query/list")
    @ResponseBody
    public Map<String,Object> getContentList(Long categoryId, Integer page, Integer rows){
        Map<String,Object> tbContentList=contentService.getContentList(categoryId,page,rows);
        return tbContentList;
    }

    @RequestMapping("/save")
    @ResponseBody
    public E3Result addContent(TbContent tbContent){
        E3Result e3Result=contentService.addContent(tbContent);
        return  e3Result;
    }
}
