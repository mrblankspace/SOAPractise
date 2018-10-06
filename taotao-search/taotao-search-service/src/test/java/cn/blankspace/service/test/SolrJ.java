package cn.blankspace.service.test;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;

import java.io.IOException;

public class SolrJ {

    @Test
    public void add(){
        SolrServer solrServer = new HttpSolrServer("http://118.126.110.120:8080/solr");
        SolrInputDocument document = new SolrInputDocument();
        document.addField("id", "test01");
        document.addField("item_title","test");
        try {
            solrServer.add(document);
            solrServer.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testquery() throws SolrServerException {
        SolrServer solrServer = new HttpSolrServer("http://118.126.110.120:8080/solr");
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("手机");
     //   solrQuery.addFilterQuery("item_price:[0 TO 8000]");
        solrQuery.set("df","item_title");
        QueryResponse response = solrServer.query(solrQuery);
        SolrDocumentList results = response.getResults();
        System.out.print("count"+results.getNumFound());
        for (SolrDocument solrDocument :results){
            System.out.println(solrDocument.get("id"));
            System.out.println(solrDocument.get("item_title"));
        }
    }
}
