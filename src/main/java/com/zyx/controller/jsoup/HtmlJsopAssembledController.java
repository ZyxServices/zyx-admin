package com.zyx.controller.jsoup;

import com.zyx.constants.Constants;
import com.zyx.file.FastDFSClient;
import com.zyx.utils.FileUploadUtils;
import com.zyx.utils.MapUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.fileupload.FileItem;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import sun.misc.BASE64Decoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by SubDong on 16-8-8.
 *
 * @author SubDong
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title HtmlJsopAssembledController
 * @package com.zyx.controller.jsoup
 * @update 16-8-8 下午4:28
 */
@Controller
@RequestMapping("/v1/jsoup")
public class HtmlJsopAssembledController {

    private Map<String, Object> objectMap = new HashMap<>();

    @RequestMapping(value = "/Assembled", method = RequestMethod.POST)
    @ApiOperation(value = "Jsoup Html 解析", notes = "Jsoup Html 解析")
    public ModelAndView Assembled(@RequestParam(name = "html", required = true) String html) {

        AbstractView jsonView = new MappingJackson2JsonView();

        List<Map<String, Object>> mapList = new ArrayList<>();
        Document document = Jsoup.parse(html);
        Elements children = document.body().children();
        children.stream().filter(x -> x != null).forEach(e -> {
            String sw = swch(e);
            if (sw != null && sw.contains("symbol")) {
                objectMap = new HashMap<>();
                objectMap.put("type", 1);
                objectMap.put(sw.split(",")[0], sw.split(",")[1]);
                mapList.add(objectMap);
            }
            List<Node> nodeList = e.childNodes();
            nodeList.stream().filter(s -> s != null && !s.equals("")).forEach(es -> {
                String s = es.nodeName();
                if (s.equals("#text")) {
                    objectMap = new HashMap<>();
                    objectMap.put("type", 2);

                    objectMap.put("text", ((TextNode) es).text());
                    mapList.add(objectMap);
                } else {
                    Elements elements = Jsoup.parse(es.toString()).body().children();
                    objectMap = new HashMap<>();
                    analyzeShunt(elements);
                    mapList.add(objectMap);
                }
            });
        });
        Map<String, Object> map = MapUtils.buildSuccessMap(Constants.SUCCESS, "解析成功", mapList);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    /**
     * 解析分流
     *
     * @param data
     * @return
     */

    private void analyzeShunt(Elements data) {
        data.forEach(e -> {
            Elements elements = e.children();
            if (elements.size() == 0) {
                String abc = e.nodeName();
                if (abc.equals("img")) {
                    objectMap.put("type", 4);
                    if (e.attr("src") != null && !e.attr("src").equals("")) {
                        String imageUrl = generateImage(e.attr("src"));
                        e.attr("src", "http://image.tiyujia.com/" + imageUrl);
                    }
                } else if (abc.equals("a")) {
                    objectMap.put("type", 5);
                    objectMap.put("text", e.html());
                } else {
                    objectMap.put("type", 2);
                    objectMap.put("text", e.html());
                }
                analyze(data.get(0));
            } else {
                analyze(e);
                if (elements.size() > 0) {
                    analyzeShunt(elements);
                }
            }
        });

    }

    /**
     * 具体解析
     *
     * @param e
     */
    private void analyze(Element e) {
        String[] split = e.attr("style").split(";");
        if (split.length > 0) {
            for (String s : split) {
                if (s.trim().contains("background-color")) {
                    objectMap.put("bgcolor", s.split(":")[1].trim());
                } else if (s.trim().contains("color")) {
                    objectMap.put("color", s.split(":")[1].trim());
                } else if (s.trim().contains("font-family")) {
                    objectMap.put("font", s.split(":")[1].trim());
                } else if (s.trim().contains("font-style")) {
                    objectMap.put("i", true);
                } else if (s.trim().contains("line-height")) {
                    objectMap.put("line-height", s.split(":")[1].trim());
                } else if (s.trim().contains("text-align")) {
                    String aling = s.split(":")[1].trim();
                    objectMap.put("text-align", aling.equals("start") ? "left" : aling.equals("center") ? "center" : aling.equals("right") ? "right" : "left");
                } else if (s.trim().contains("width")) {
                    objectMap.put("w", s.split(":")[1].trim());
                } else if (s.trim().contains("height")) {
                    objectMap.put("h", s.split(":")[0].trim());
                }
            }

        }
        String swch = swch(e);

        if (swch != null) {
            if (swch.equals("fontTow")) {
                objectMap.put("color", e.attr("color"));
                objectMap.put("face", e.attr("face"));
            } else if (swch.equals("color")) {
                objectMap.put(swch, e.attr("color"));
            } else if (swch.equals("face")) {
                objectMap.put(swch, e.attr("face"));
            } else if (swch.equals("img-url")) {
                objectMap.put(swch, e.attr("src"));
            } else if (swch.equals("url")) {
                objectMap.put(swch, e.attr("href"));
            } else {
                if (swch.contains("symbol")) {
                    objectMap.put("type", 1);
                    objectMap.put(swch.split(",")[0], swch.split(",")[1]);
                } else {
                    objectMap.put(swch, true);
                }
            }
        }
    }

    /**
     * 验证标签类型
     *
     * @param e
     * @return
     */
    private String swch(Element e) {
        String nodeName = e.nodeName();
        switch (nodeName) {
            case "b":
                return "b";
            case "u":
                return "u";
            case "font":
                if ((e.attr("color") != null && !e.attr("color").equals("")) && (e.attr("face") != null && !e.attr("face").equals(""))) {
                    return "fontTow";
                } else if (e.attr("color") != null && !e.attr("color").equals("")) {
                    return "color";
                } else if (e.attr("face") != null && !e.attr("face").equals("")) {
                    return "face";
                }
                break;
            case "br":
                return "symbol,1";
            case "p":
                return "symbol,3";
            case "img":
                return "img-url";
            case "a":
                return "url";
        }
        return null;
    }

    /**
     * base64字符串转化成图片(并转换成byte 上传至文件服务器)
     *
     * @param imgStr
     * @return
     */
    private String generateImage(String imgStr) {
        if (imgStr == null) //图像数据为空
            return null;
        String imageBase64 = imgStr.substring(imgStr.indexOf("base64,") + 7);
        if (imageBase64.equals("")) return null;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imageBase64);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            String uploadFiles = FastDFSClient.uploadFiles(b, "zyx.jpg");
            return uploadFiles;
        } catch (Exception e) {
            return "";
        }
    }


}
