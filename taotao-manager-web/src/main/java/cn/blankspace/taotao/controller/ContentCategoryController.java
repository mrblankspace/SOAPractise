package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.content.service.ContentCategoryService;
import cn.blankspace.taotao.pojo.EasyUITreeNode;
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
}
