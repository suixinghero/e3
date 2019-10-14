package org.e3.door.controller;

import org.e3.content.service.ContentService;
import org.e3.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**首页展示Controller
 * @author xujin
 * @package-name org.e3.door.controller
 * @createtime 2019-10-07 19:23
 */
@Controller
public class IndexController {
    @Autowired
    private ContentService contentService;
    @Value("${content_lunbo_id}")
    private String categoryId;


    @RequestMapping("/index")
    public ModelAndView showIndex(){
        //查询内容列表
        List<TbContent> contentList=contentService.getContentListByCategoryId(new Long(categoryId));
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("ad1List",contentList);
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
