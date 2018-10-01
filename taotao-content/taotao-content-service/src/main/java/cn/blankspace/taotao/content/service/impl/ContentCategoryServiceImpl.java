package cn.blankspace.taotao.content.service.impl;

import cn.blankspace.taotao.content.service.ContentCategoryService;
import cn.blankspace.taotao.mapper.TbContentCategoryMapper;
import cn.blankspace.taotao.pojo.EasyUITreeNode;
import cn.blankspace.taotao.pojo.TbContentCategory;
import cn.blankspace.taotao.pojo.TbContentCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
