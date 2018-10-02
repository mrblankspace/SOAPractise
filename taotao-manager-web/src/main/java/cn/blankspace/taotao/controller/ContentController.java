package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.content.service.ContentService;
import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.pojo.TbContent;
import cn.blankspace.util.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;

    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows){
        EasyUIDataGridResult list = contentService.getContentList(categoryId, page, rows);
        return  list;
    }

    @RequestMapping(value = "content/save",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult saveContent(TbContent content){
        return contentService.saveContent(content);
    }
}
