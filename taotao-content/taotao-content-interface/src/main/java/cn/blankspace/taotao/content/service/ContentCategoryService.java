package cn.blankspace.taotao.content.service;

import cn.blankspace.taotao.pojo.EasyUITreeNode;

import java.util.List;

/**
 * 内容分类服务
 */
public interface ContentCategoryService {
    /**
     * 查询内容分类服务
     * @param parentId
     * @return
     */
    public List<EasyUITreeNode>  getContentCategoryList(Long parentId);
}
