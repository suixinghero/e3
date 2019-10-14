package org.e3.controller;

import org.e3.common.pojo.EasyUITreeNode;
import org.e3.common.util.E3Result;
import org.e3.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**内容分类管理的controller
 * @author xujin
 * @package-name org.e3.controller
 * @createtime 2019-10-08 10:37
 */
@Controller
@RequestMapping("/content")
public class ContentCatController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/category/list")
    @ResponseBody
    public List<EasyUITreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> easyUITreeNodeList=contentCategoryService.getContentCatList(parentId);
        return  easyUITreeNodeList;
    }


    /**
     * 添加分类节点
     * @param parentId
     * @param name
     * @return
     */
    @RequestMapping(value = "/category/create",method = RequestMethod.POST)
    @ResponseBody
    public E3Result addContentCategory(Long parentId,String name){
        E3Result e3Result=contentCategoryService.addContentCategory(parentId,name);
        return e3Result;
    }


    /**
     * 删除分类节点
     * @param id
     * @return
     */
    @RequestMapping(value = "/category/delete",method = RequestMethod.POST)
    @ResponseBody
    public E3Result deleteContentCategory(Long id){
        E3Result e3Result=contentCategoryService.deleteContentCategory(id);
        return e3Result;
    }

    /**
     * 更新分类节点
     * @param id
     * @param name
     * @return
     */
    @RequestMapping(value = "/category/update",method = RequestMethod.POST)
    @ResponseBody
    public E3Result updateContentCategory(Long id,String name){
        E3Result e3Result=contentCategoryService.updateContentCategory(id,name);
        return  e3Result;
    }
}
