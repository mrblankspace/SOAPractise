package cn.blankspace.taotao.controller;

import cn.blankspace.taotao.content.service.ContentService;
import cn.blankspace.taotao.pojo.Ad1Node;
import cn.blankspace.taotao.pojo.TbContent;
import cn.blankspace.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 门户系统
 */
@Controller
public class IndexController {

    @Autowired
    private ContentService contentService;

    @Value("${AD1_CATEGORY_ID}")
    private  Long AD1_CATEGORY_ID;

    @Value("${AD1_HEIGHT_B}")
    private String AD1_HEIGHT_B;

    @Value("${AD1_HEIGHT}")
    private String AD1_HEIGHT;

    @Value("${AD1_WIDTH_B}")
    private  String AD1_WIDTH_B;

    @Value("${AD1_WIDTH}")
    private  String AD1_WIDTH;

    @RequestMapping("/index")
    public String showIndex(Model model){
        //根据内容分类的id, 查找内容列表
        List<TbContent> contentList = contentService.getContentListByCatId(AD1_CATEGORY_ID);
        List<Ad1Node> nodes = new ArrayList<>();
        for (TbContent tbContent : contentList) {
            Ad1Node node = new Ad1Node();
            node.setAlt(tbContent.getSubTitle());
            node.setHeight(AD1_HEIGHT);
            node.setHeightB(AD1_HEIGHT_B);
            node.setHref(tbContent.getUrl());
            node.setSrc(tbContent.getPic());
            node.setSrcB(tbContent.getPic2());
            node.setWidth(AD1_WIDTH);
            node.setWidthB(AD1_WIDTH_B);
            nodes.add(node);
        }
        model.addAttribute("ad1",JsonUtils.objectToJson(nodes));
        return "index";
    }
}
