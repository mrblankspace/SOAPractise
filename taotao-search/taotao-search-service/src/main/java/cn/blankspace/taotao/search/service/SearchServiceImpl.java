package cn.blankspace.taotao.search.service;

import cn.blankspace.search.service.SearchService;
import cn.blankspace.taotao.pojo.SearchItem;
import cn.blankspace.taotao.pojo.SearchResult;
import cn.blankspace.taotao.search.mapper.SearchItemMapper;
import cn.blankspace.util.TaotaoResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.util.List;

/**
 * 商品导出接口实现类
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchItemMapper searchItemMapper;

    @Autowired
    private SolrServer solrServer;

    @Autowired
    private SearchDao searchDao;

    @Override
    public TaotaoResult inputAllSearchItems() throws IOException, SolrServerException {
        System.out.print(searchItemMapper);
        List<SearchItem> list = searchItemMapper.getSearchItemList();
        for (SearchItem searchItem : list){
            SolrInputDocument document = new SolrInputDocument();
            document.addField("id",searchItem.getId().toString());
            document.addField("item_title",searchItem.getTitle());
            document.addField("item_sell_point",searchItem.getSell_point());
            document.addField("item_price",searchItem.getPrice());
            document.addField("item_image",searchItem.getImage());
            document.addField("item_category_name",searchItem.getCategory_name());
            document.addField("item_desc",searchItem.getItem_desc());
            solrServer.add(document);
        }
        solrServer.commit();
        return TaotaoResult.ok();
    }

    @Override
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception {
        SolrQuery query = new SolrQuery();
        if (StringUtils.isNotBlank(queryString)) {
            query.setQuery(queryString);
        } else {
            query.setQuery("*:*");
        }
        if (page == null) {
            page= 1;
        }
        if (rows == null) {
            rows = 60;
        }
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df", "item_keywords");
        query.setHighlight(true);
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        query.addHighlightField("item_title");

        SearchResult search = searchDao.search(query);
        long pageCount = 0;
        pageCount = search.getPageCount()/rows;
        if (search.getPageCount()%rows > 0){
            pageCount++;
        }
        search.setPageCount(pageCount);
        return search;
    }
}