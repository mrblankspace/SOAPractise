package cn.blankspace.taotao.content.service.impl;

import cn.blankspace.taotao.content.service.ContentService;
import cn.blankspace.taotao.mapper.TbContentMapper;
import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.pojo.TbContent;
import cn.blankspace.taotao.pojo.TbContentExample;
import cn.blankspace.taotao.pojo.TbItem;
import cn.blankspace.util.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容服务实现类
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentMapper contentMapper;

    @Override
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {
        PageHelper.startPage(page,rows);
        EasyUIDataGridResult result = new EasyUIDataGridResult();
        //封装总数
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> tbContents = contentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        result.setTotal((int) pageInfo.getTotal());
        result.setRows(tbContents);
        //查询内容列表
        return result;
    }

    @Override
    public TaotaoResult saveContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(content.getCreated());
        contentMapper.insertSelective(content);
        return TaotaoResult.ok();
    }
}
