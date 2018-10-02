package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.content.service.ContentCategoryService;
import cn.blankspace.taotao.pojo.EasyUITreeNode;
import cn.blankspace.util.TaotaoResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping(value = "/content/category/list",method= RequestMethod.GET)
    @ResponseBody
    public List<EasyUITreeNode> getContentCategoryList(@RequestParam(value = "id",defaultValue = "0")Long parentId){
        List<EasyUITreeNode> list = contentCategoryService.getContentCategoryList(parentId);
        return list;
    }

    @RequestMapping(value = "/content/category/create",method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createContentCategory(Long parentId, String name){
        return contentCategoryService.createContentCategory(parentId,name);
    }

    @RequestMapping(value = "/content/category/update",method = RequestMethod.POST)
    @ResponseBody
    public void updateContentCategory(Long id, String name){
        contentCategoryService.renameContentCategory(id,name);
    }
}
