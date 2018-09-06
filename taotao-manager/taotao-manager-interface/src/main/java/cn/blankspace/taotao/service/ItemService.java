package cn.blankspace.taotao.service;

import cn.blankspace.taotao.pojo.EasyUIDataGridResult;

public interface ItemService {

    /**
     * 分页查询商品列表
     * @param page 当前页数
     * @param rows 总页数
     * @return
     */
    public EasyUIDataGridResult getItemList(int page, int rows);
}
