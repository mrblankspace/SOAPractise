package cn.blankspace.taotao.content.service.impl;

import cn.blankspace.taotao.content.service.ContentCategoryService;
import cn.blankspace.taotao.mapper.TbContentCategoryMapper;
import cn.blankspace.taotao.pojo.EasyUITreeNode;
import cn.blankspace.taotao.pojo.TbContentCategory;
import cn.blankspace.taotao.pojo.TbContentCategoryExample;
import cn.blankspace.util.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<EasyUITreeNode> getContentCategoryList(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        List<EasyUITreeNode> nodes = new ArrayList<>();
        for (TbContentCategory tbContentCategory:list){
            EasyUITreeNode node = new EasyUITreeNode();
            node.setId(tbContentCategory.getId());
            node.setState(tbContentCategory.getIsParent()?"closed":"open");
            node.setText(tbContentCategory.getName());
            nodes.add(node);
        }
        return nodes;
    }

    /**
     * 增加分类节点
     * @param parentId 父节点id
     * @param name  名称
     * @return
     */
    @Override
    public TaotaoResult createContentCategory(Long parentId, String name) {
        TbContentCategory category = new TbContentCategory();
        category.setCreated(new Date());
        category.setIsParent(false);
         category.setName(name);
         category.setParentId(parentId);
         category.setSortOrder(1);
         category.setStatus(1);
         category.setUpdated(category.getCreated());
         tbContentCategoryMapper.insertSelective(category);
         TbContentCategory parent = tbContentCategoryMapper.selectByPrimaryKey(parentId);
         if (parent.getIsParent() == false){
             parent.setIsParent(true);
             tbContentCategoryMapper.updateByPrimaryKeySelective(parent);
         }
        return TaotaoResult.ok(category);
    }

    @Override
    public TaotaoResult renameContentCategory(Long id, String name) {
        System.out.println(id+" "+name);
        System.out.println(tbContentCategoryMapper);
        TbContentCategory category = tbContentCategoryMapper.selectByPrimaryKey(id);
        category.setName(name);
        category.setUpdated(new Date());
        tbContentCategoryMapper.updateByPrimaryKeySelective(category);
        return TaotaoResult.ok(category);
    }

    @Override
    public TaotaoResult deleteContentCategory(Long id) {
        return null;
    }


}
