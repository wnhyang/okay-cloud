package cn.wnhyang.okay.framework.web.core.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Safelist;

/**
 * @author wnhyang
 * @date 2024/2/1
 **/
public class JsoupUtil {

    private static final Safelist SAFELIST = Safelist.relaxed();
    /*
     * 配置过滤化参数,不对代码进行格式化
     */
    private static final Document.OutputSettings OUTPUT_SETTINGS = new Document.OutputSettings().prettyPrint(false);

    static {
        // 富文本编辑时一些样式是使用 style 来进行实现的
        // 比如红色字体 style="color:red;", 所以需要给所有标签添加 style 属性
        // 注意：style 属性会有注入风险 <img STYLE="background-image:url(javascript:alert('XSS'))">
        SAFELIST.addAttributes(":all", "style", "class");
        // 保留 a 标签的 target 属性
        SAFELIST.addAttributes("a", "target");
        // 支持img 为base64
        SAFELIST.addProtocols("img", "src", "data");

        // 保留相对路径, 保留相对路径时，必须提供对应的 baseUri 属性，否则依然会被删除
        // WHITELIST.preserveRelativeLinks(false);

        // 移除 a 标签和 img 标签的一些协议限制，这会导致 xss 防注入失效，如 <img src=javascript:alert("xss")>
        // 虽然可以重写 WhiteList#isSafeAttribute 来处理，但是有隐患，所以暂时不支持相对路径
        // WHITELIST.removeProtocols("a", "href", "ftp", "http", "https", "mailto");
        // WHITELIST.removeProtocols("img", "src", "http", "https");
    }

    public static String clean(String content) {
        return Jsoup.clean(content, "", SAFELIST, OUTPUT_SETTINGS);
    }
}
