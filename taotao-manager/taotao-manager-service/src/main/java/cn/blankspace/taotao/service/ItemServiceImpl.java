package cn.blankspace.taotao.service;

import cn.blankspace.taotao.mapper.TbItemMapper;
import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.pojo.TbItem;
import cn.blankspace.taotao.pojo.TbItemExample;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements  ItemService{
    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public EasyUIDataGridResult getItemList(int page, int rows) {
        PageHelper.startPage(page,rows);
        TbItemExample tbItemExample = new TbItemExample();
        List<TbItem> tbItems = tbItemMapper.selectByExample(tbItemExample);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        result.setRows(tbItems);
        result.setTotal(rows);
        return result;
    }
}
