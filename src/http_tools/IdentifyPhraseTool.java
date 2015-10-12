package http_tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 鉴定中文字符串是否为词组
 * 
 * @author steven
 *
 */

public class IdentifyPhraseTool {

	public static final String api = "http://tool.xdf.cn/ch/search_";
	public static final String suffix = "_1.html";

	public static boolean identifyPhrase(String phrase) {

		String htmlStr;
		try {
			htmlStr = HttpRequest.sendGet(
					api + URLEncoder.encode(phrase, "utf-8") + suffix, "",
					"utf-8");
			if (htmlStr.indexOf("<em>" + phrase) != -1)
				return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
