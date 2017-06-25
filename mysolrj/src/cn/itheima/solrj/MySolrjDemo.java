package cn.itheima.solrj;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.apache.solr.common.params.SolrParams;
import org.junit.Test;

public class MySolrjDemo {

	//创建和修改索引
	@Test
	public void testCreateAndUpdateIndex() throws Exception{
		// 1. 创建HttpSolrServer对象
		// 设置solr服务接口,浏览器客户端地址http://127.0.0.1:8080/solr/#/
		String baseURL = "http://127.0.0.1:8080/solr";
		HttpSolrServer server = new HttpSolrServer(baseURL);
		
		// 2. 创建SolrInputDocument对象
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", "1");
		document.addField("price", "76.5");
		
		//把document添加到索引库里
		server.add(document);
		
		//提交
		server.commit();
	}
	
	//删除索引
	@Test
	public void deleteIndex() throws Exception{
		//1创建httpsolrserver对象
		String baseURL = "http://127.0.0.1:8080/solr";
		HttpSolrServer server = new HttpSolrServer(baseURL);
		
		//根据id删除
		//server.deleteById("1");
		//*:*表示删除所有 慎用
//		server.deleteByQuery("*:*");
		//根据域名删除
		server.deleteByQuery("name:lucene");
		
		//提交
		server.commit();
	}
	
	//查询索引
	@Test
	public void testSeacherIndex() throws Exception{
		//1创建httpsolrserver对象
		String baseURL = "http://127.0.0.1:8080/solr";
		HttpSolrServer server = new HttpSolrServer(baseURL);
		
		SolrQuery query = new SolrQuery();
		//查询条件 q代表query val是条件
		query.set("q", "name:helloworld");
		//返回结果
		QueryResponse response = server.query(query);
		//结果集
		SolrDocumentList results = response.getResults();
		//遍历结果集
		for (SolrDocument solrDocument : results) {
			System.out.println(solrDocument.get("id"));
			System.out.println(solrDocument.get("name"));
		}
	}
}
