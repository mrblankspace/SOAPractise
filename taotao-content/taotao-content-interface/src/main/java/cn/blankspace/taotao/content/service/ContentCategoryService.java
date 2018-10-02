package cn.blankspace.taotao.content.service;

import cn.blankspace.taotao.pojo.EasyUITreeNode;
import cn.blankspace.util.TaotaoResult;

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

    /**
     *
     * @param parentId 父节点id
     * @param name  名称
     * @return
     */
    public TaotaoResult createContentCategory(Long parentId, String name);


    public TaotaoResult renameContentCategory(Long id, String name);

    public TaotaoResult deleteContentCategory(Long id);
}
