package com.jxkj.service.impl;

import com.alibaba.fastjson.JSON;
import com.jxkj.model.JDContent;
import com.jxkj.service.JDContentService;
import com.jxkj.utils.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.Highlighter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
@Service
public class JDContentServiceImpl implements JDContentService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public boolean parse(String keywords) {
        List<JDContent> jdContents = new ArrayList<>();
        try {
            jdContents = HtmlParseUtil.parserJD(keywords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BulkRequest bulkRequest = new BulkRequest();
        bulkRequest.timeout("2m");
        for (int i = 0; i < jdContents.size(); i++) {
            bulkRequest.add(
                    new IndexRequest("jd_goods")
                    .source(JSON.toJSONString(jdContents.get(i)), XContentType.JSON)
            );
        }
        BulkResponse bulk = null;
        try {
            bulk = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return !bulk.hasFailures();
    }

    @Override
    public List<Map<String, Object>> search(String type, String keywords, int currentPage, int pageSize) {
        if (currentPage <= 1) {
            currentPage = 1;
        }
        // 构建搜索请求
        SearchRequest searchRequest = new SearchRequest("jd_goods");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

        // 分页
        searchSourceBuilder.from(currentPage);
        searchSourceBuilder.size(pageSize);

        // 高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.field("title");
        highlightBuilder.preTags("<span style='color:red'>");
        highlightBuilder.postTags("</span>");
        searchSourceBuilder.highlighter(highlightBuilder);

        // 精准匹配
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(type, keywords);
        searchSourceBuilder.query(termQueryBuilder);
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // 执行搜索
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = null;
        try {
           searchResponse  = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 解析结果
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit searchHit : searchResponse.getHits().getHits()) {
            Map<String, HighlightField> map = searchHit.getHighlightFields();
            HighlightField field = map.get("title");
            Map<String, Object> sourceAsMap = searchHit.getSourceAsMap();
            if (field != null) {
                Text[] fragments = field.fragments();
                String new_title = "";
                for (Text fragment : fragments) {
                    new_title += fragment;
                }
                sourceAsMap.put("title", new_title);
            }
            list.add(sourceAsMap);
        }
        return list;
    }
}
