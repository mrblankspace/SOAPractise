package cn.blankspace.taotao.controller;

import cn.blankspace.search.service.SearchService;
import cn.blankspace.util.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 索引导入
 */
@Controller
public class IndexController {
    @Autowired
    private SearchService searchService;

    @RequestMapping("/index/importall")
    @ResponseBody
    public TaotaoResult importAll() throws IOException, SolrServerException {
        return searchService.inputAllSearchItems();
    }

}
