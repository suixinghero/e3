package org.e3.controller;

import org.e3.common.pojo.EasyUITreeNode;
import org.e3.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**商品分类Controller
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-05 12:14
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @ResponseBody
    @RequestMapping("/list")
    public List<EasyUITreeNode> getItemCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> easyUITreeNodeList=itemCatService.getItemCatList(parentId);
        return  easyUITreeNodeList;
    }
}
