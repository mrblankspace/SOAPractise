package cn.blankspace.taotao.search.controller;

import cn.blankspace.search.service.SearchService;
import cn.blankspace.taotao.pojo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLDecoder;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    /**
     * 根据条件搜索
     * @param page
     * @param queryString
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam(defaultValue = "1") Integer page, @RequestParam(value = "q") String queryString, Model model) throws Exception {
        queryString = new String(queryString.getBytes("iso-8859-1"),"utf-8");
        SearchResult search = searchService.search(queryString, page, 30);
        //System.out.print(search);
        model.addAttribute("query",queryString);
        model.addAttribute("page",page);
        model.addAttribute("totalPages",search.getPageCount());
        model.addAttribute("itemList",search.getItemList());
        return  "search";
    }
}
