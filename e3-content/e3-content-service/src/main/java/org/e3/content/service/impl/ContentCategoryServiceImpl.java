package org.e3.content.service.impl;

import org.e3.common.pojo.EasyUITreeNode;
import org.e3.common.util.E3Result;
import org.e3.content.service.ContentCategoryService;
import org.e3.mapper.TbContentCategoryMapper;
import org.e3.pojo.TbContentCategory;
import org.e3.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**内容分类管理Service
 * @author xujin
 * @package-name org.e3.content.service.impl
 * @createtime 2019-10-08 10:21
 */
@Service("contentCategoryService")
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<EasyUITreeNode> getContentCatList(Long parentId) {
        List<TbContentCategory> tbContentCategoryList=getSonNodeByParentId(parentId);
        List<EasyUITreeNode> easyUITreeNodeList=new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategoryList) {
            EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
            easyUITreeNode.setId(tbContentCategory.getId());
            easyUITreeNode.setText(tbContentCategory.getName());
            easyUITreeNode.setState(tbContentCategory.getIsParent()?"closed":"open");
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }

    @Override
    public E3Result addContentCategory(Long parentId, String name) {
        //创建一个tb_content_category表对应的pojo对象
        TbContentCategory tbContentCategory=new TbContentCategory();
        //设置pojo的属性
        tbContentCategory.setParentId(parentId);
        tbContentCategory.setName(name);
        //可选值:1(正常),2(删除)
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        //该类目是否为父类目，1为true，0为false,新增节点一定是叶子节点
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setUpdated(new Date());
        //插入数据库
        tbContentCategoryMapper.insert(tbContentCategory);
        //判断父节点的isparent的属性，如果不是true，改为true;
        //根据patentId查询父节点
        TbContentCategory parent=tbContentCategoryMapper.selectByPrimaryKey(parentId);
        if(!parent.getIsParent()){
            //更新数据库
            parent.setIsParent(true);
            tbContentCategoryMapper.updateByPrimaryKey(parent);
        }
        //返回结果，返回E3Result,包含pojo
        return E3Result.ok(tbContentCategory);
    }

    private List<TbContentCategory> getSonNodeByParentId(Long parentId){
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria tbContentCategoryExampleCriteria = tbContentCategoryExample.createCriteria();
        tbContentCategoryExampleCriteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategoryList = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        return tbContentCategoryList;
    }

    @Override
    public E3Result deleteContentCategory(Long id) {
        //判断是否删除的是父节点，如果是则不允许删除
        List<TbContentCategory> tbContentCategoryList1 = getSonNodeByParentId(id);
        if(tbContentCategoryList1.size() == 0) {
            //获取儿子节点
            TbContentCategory son = tbContentCategoryMapper.selectByPrimaryKey(id);
            //获取父节点的id
            Long parentId = son.getParentId();
            //判断叶子节点所属的父亲节点有几个子节点
            List<TbContentCategory> tbContentCategoryList2 = getSonNodeByParentId(parentId);
            if (tbContentCategoryList2.size() == 1) {
                //删除叶子节点
                tbContentCategoryMapper.deleteByPrimaryKey(id);
                //根据patentId查询父节点
                TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
                //更新数据库
                parent.setIsParent(false);
                tbContentCategoryMapper.updateByPrimaryKey(parent);
                return E3Result.build(200, null, null);
            }else{
                //删除叶子节点
                tbContentCategoryMapper.deleteByPrimaryKey(id);
                return E3Result.build(200, null, null);
            }
        }else {
            return E3Result.build(500, null, null);
        }
    }


    @Override
    public E3Result updateContentCategory(Long id, String name) {
        TbContentCategory tbContentCategory=new TbContentCategory();
        tbContentCategory.setId(id);
        tbContentCategory.setName(name);
        tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
        return E3Result.ok();
    }
}
