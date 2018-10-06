package cn.blankspace.search.service;

import cn.blankspace.taotao.pojo.SearchResult;
import cn.blankspace.util.TaotaoResult;
import org.apache.solr.client.solrj.SolrServerException;

import java.io.IOException;

/**
 *
 * @author zhangbo
 */
public interface SearchService {
    /**
     * 导入所有的商品到索引库中
     * @return
     */
    public TaotaoResult inputAllSearchItems() throws IOException, SolrServerException, org.apache.solr.client.solrj.SolrServerException;

    /**
     *
     * @param queryString 主条件
     * @param page   页码
     * @param rows  行数
     * @return
     */
    public SearchResult search(String queryString, Integer page, Integer rows) throws Exception;
}
