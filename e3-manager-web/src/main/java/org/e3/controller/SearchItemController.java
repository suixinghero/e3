package org.e3.controller;

import org.e3.common.util.E3Result;
import org.e3.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**导入商品数据到索引库
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-11 20:32
 */
@Controller
public class SearchItemController {
    @Autowired
    private SearchItemService searchItemService;
    /**
     * 导入商品数据到索引库
     * @return
     */
    @RequestMapping("/index/item/import")
    @ResponseBody
    public E3Result importAllItem(){
        E3Result e3Result=searchItemService.importAllItem();
        return  e3Result;
    }
}
