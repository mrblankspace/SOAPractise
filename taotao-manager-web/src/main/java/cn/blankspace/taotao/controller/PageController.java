package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.pojo.EasyUIDataGridResult;
import cn.blankspace.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    @RequestMapping("/{page}")
    public String showItemList(@PathVariable String page){
        return page;
    }

    /**
     * 分页
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
        System.out.println("page"+page+"rows"+rows);
        EasyUIDataGridResult result = itemService.getItemList(page,rows);
        System.out.println(result.getRows().get(0));
        System.out.println(result.getTotal());
        return result;
    }
}
