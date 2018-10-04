package cn.blankspace.taotao.content.service.impl;

import cn.blankspace.taotao.content.jedis.JedisClient;
import cn.blankspace.taotao.content.service.ContentService;
import cn.blankspace.taotao.mapper.TbContentMapper;
import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.pojo.TbContent;
import cn.blankspace.taotao.pojo.TbContentExample;
import cn.blankspace.taotao.pojo.TbItem;
import cn.blankspace.util.JsonUtils;
import cn.blankspace.util.TaotaoResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Autowired
    JedisClient client;

    @Value("${CONTENT_KEY}")
    private String CONTENT_KEY;

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
        try{
            //插入后要清空缓存  以便缓存的更新
            client.hdel(CONTENT_KEY,content.getCategoryId()+"");
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> getContentListByCatId(Long categoryId) {
        try {
            String jsonStr = client.hget(CONTENT_KEY, categoryId + "");
            if (StringUtils.isNotBlank(jsonStr)){
                System.out.print("缓存"+jsonStr);
                return JsonUtils.jsonToList(jsonStr,TbContent.class);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        TbContentExample example = new TbContentExample();
        example.createCriteria().andCategoryIdEqualTo(categoryId);
        List<TbContent> list = contentMapper.selectByExample(example);

        try {
            client.hset(CONTENT_KEY,categoryId+"", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
