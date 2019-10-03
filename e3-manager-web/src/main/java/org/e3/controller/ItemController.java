package org.e3.controller;

import org.e3.pojo.TbItem;
import org.e3.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-02 22:41
 */
@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;


    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable(value = "itemId")Long itemId){
       TbItem tbItem= itemService.getItemlById(itemId);
       return tbItem;
    }
}
