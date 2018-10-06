package cn.blankspace.taotao.search.mapper;

import cn.blankspace.taotao.pojo.SearchItem;

import java.util.List;

/**
 *  关联 item item-cat item-desc 三张表查询
 * @author zhangbo
 */
public interface SearchItemMapper {
    /**
     * 查询所有商品的数据
     * @return
     */
     List<SearchItem> getSearchItemList();
}
