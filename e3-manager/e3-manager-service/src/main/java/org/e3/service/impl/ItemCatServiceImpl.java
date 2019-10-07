package org.e3.service.impl;

import org.e3.common.pojo.EasyUITreeNode;
import org.e3.mapper.TbItemCatMapper;
import org.e3.pojo.TbItemCat;
import org.e3.pojo.TbItemCatExample;
import org.e3.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**商品分类管理
 * @author xujin
 * @package-name org.e3.service.impl
 * @createtime 2019-10-05 11:50
 */
@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<EasyUITreeNode> getItemCatList(Long patentId) {
        TbItemCatExample tbItemCatExample=new TbItemCatExample();
        TbItemCatExample.Criteria tbItemCatExampleCriteria=tbItemCatExample.createCriteria();
        tbItemCatExampleCriteria.andParentIdEqualTo(patentId);
        List<TbItemCat> tbItemCatList=tbItemCatMapper.selectByExample(tbItemCatExample);
        List<EasyUITreeNode> easyUITreeNodeList=new ArrayList<>();
        for (TbItemCat tbItemCat : tbItemCatList) {
            EasyUITreeNode easyUITreeNode=new EasyUITreeNode();
            easyUITreeNode.setId(tbItemCat.getId());
            easyUITreeNode.setText(tbItemCat.getName());
            easyUITreeNode.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUITreeNodeList.add(easyUITreeNode);
        }
        return easyUITreeNodeList;
    }
}
