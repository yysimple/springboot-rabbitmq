package com.jxkj.utils;

import com.jxkj.model.JDContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 *
 * @author wcx
 * @version 1.0
 */
public class HtmlParseUtil {
    public static void main(String[] args) throws Exception {
        parserJD("码出高效").forEach(System.out::println);
    }

    public static List<JDContent> parserJD(String keywords) throws Exception {
        String url = "https://search.jd.com/Search?keyword=" + keywords + "enc=utf-8";

        Document document = Jsoup.parse(new URL(url), 30000);

        Element element = document.getElementById("J_goodsList");

        Elements elements = document.getElementsByTag("li");

        List<JDContent> jdContents = new ArrayList<>();
        for (Element el : elements) {
            JDContent jdContent = new JDContent();
            String img = el.getElementsByTag("img").eq(0).attr("src");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            if (!StringUtils.isEmpty(img) && !StringUtils.isEmpty(price) && !StringUtils.isEmpty(title)) {
                jdContent.setImg(img);
                jdContent.setPrice(price);
                jdContent.setTitle(title);
                jdContents.add(jdContent);
            }
        }
        return jdContents;
    }
}
