package cn.blankspace.taotao.search.service;

import cn.blankspace.taotao.pojo.SearchItem;
import cn.blankspace.taotao.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 从索引库中搜索的dao
 */
@Repository
public class SearchDao {
    @Autowired
    private SolrServer solrServer;
    /**
     * 根据查询条件查询结果集
     * @param query
     * @return
     * @throws Exception
     */
    public SearchResult search(SolrQuery query) throws  Exception{
        QueryResponse response = solrServer.query(query);
        SolrDocumentList results = response.getResults();
        SearchResult result = new SearchResult();
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
        List<SearchItem> list = new ArrayList<>();
        for (SolrDocument document:results){
            SearchItem item = new SearchItem();
            item.setCategory_name(document.get("item_category_name").toString());
            item.setId(Long.parseLong(document.get("id").toString()));
            item.setImage(document.get("item_image").toString());
            item.setPrice((Long)document.get("item_price"));
            //可以不用详情描述
            //item.setItem_desc(document.get("item_desc").toString());
            item.setSell_point(document.get("item_sell_point").toString());
            String gaoLiangStr = "";
            List<String> gaoLiangList = highlighting.get(document.get("id")).get("item_title");
            if (gaoLiangList != null && gaoLiangList.size()>0){
                gaoLiangStr = gaoLiangList.get(0);
            } else {
                gaoLiangStr = document.get("item_title").toString();
            }
            item.setTitle(gaoLiangStr);
            list.add(item);
        }
        result.setItemList(list);
        result.setRecordCount(results.getNumFound());
        return result;
    }
}
