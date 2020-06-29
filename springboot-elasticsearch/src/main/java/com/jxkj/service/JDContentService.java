package com.jxkj.service;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public interface JDContentService {
    /**
     * 功能描述: 解析数据放到es中
     *
     * @Author wcx
     * @param keywords
     * @return boolean
     **/
    boolean parse(String keywords);

    /**
     * 通过es进行数据查询
     * @param keywords
     * @param currentPage
     * @param pageSize
     * @param type
     * @return
     */
    List<Map<String, Object>> search(String type, String keywords, int currentPage, int pageSize);
}
