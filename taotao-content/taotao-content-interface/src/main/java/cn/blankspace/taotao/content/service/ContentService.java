package cn.blankspace.taotao.content.service;

import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.pojo.TbContent;
import cn.blankspace.util.TaotaoResult;

/**
 * 内容服务
 */
public interface ContentService {
    /**
     * 获取内容列表
     * @param categoryId
     * @param page
     * @param rows
     * @return
     */
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows);

    public TaotaoResult saveContent(TbContent content);
}
