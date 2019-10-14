package org.e3.search.controller;

import org.e3.common.pojo.SearchResult;
import org.e3.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**商品搜索controller
 * @author xujin
 * @package-name org.e3.search.controller
 * @createtime 2019-10-12 16:01
 */
@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Value("${item_rows}")
    private Integer rows;
    /**商品搜索
     *
     * @param keyword
     * @param page
     * @return
     */
    @RequestMapping("/search")
    public ModelAndView searchItemList(String keyword,@RequestParam(defaultValue = "1")Integer page) throws Exception {
        keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
        SearchResult searchResult=searchService.search(keyword,page,rows);
        //把查询结果传给页面
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("query",keyword);
        modelAndView.addObject("totalPages",searchResult.getTotalPages());
        modelAndView.addObject("recourdCount",searchResult.getRecourdCount());
        modelAndView.addObject("page",page);
        modelAndView.addObject("itemList",searchResult.getSearchItemList());
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
