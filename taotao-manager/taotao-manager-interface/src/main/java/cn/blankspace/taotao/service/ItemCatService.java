package cn.blankspace.taotao.service;

import cn.blankspace.taotao.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    public List<TbItemCat> getItemCatList(Long parentId);
}
