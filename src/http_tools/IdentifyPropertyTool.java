package http_tools;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * 获取中文字的字性
 * 
 * @author steven
 *
 */
public class IdentifyPropertyTool {

	public static final String api = "http://zidian.eduu.com/search";

	public static final String api2 = "http://s.diyifanwen.com/ZdSearch.asp";

	public static ArrayList<String> identifyProperty(String word) {
		ArrayList<String> property = new ArrayList<>();
		String htmlStr = HttpRequest.sendGet(api, "name=" + word, null);
		int cursor = 0;
		while ((cursor = htmlStr.indexOf("【", cursor)) != -1) {
			String midStr = htmlStr.substring(cursor + 1, cursor + 2);
			cursor++;
			for (String inStr : property) {
				if (inStr.equals(midStr))
					continue;
			}
			property.add(midStr);
		}
		return property;
	}

	public static ArrayList<String> identifyProperty2(String word) {
		ArrayList<String> property = new ArrayList<>();
		try {
			String htmlStr = HttpRequest.sendGet(api2,
					"Query=" + URLEncoder.encode(word, "gbk")
							+ "&Submit=%D7%D6%B5%E4%B2%E9%D1%AF&Search=SWord",
					"gb2312");
			if (htmlStr.equals(HttpRequest.NETERROR)) {
				return null;
			}
			// htmlStr = new String(htmlStr.getBytes("utf8"),"gbk");
			int cursor = 0;
			while ((cursor = htmlStr.indexOf("【", cursor)) != -1) {
				String midStr = htmlStr.substring(cursor + 1, cursor + 2);
				cursor++;
				boolean isIn = false;
				for (String inStr : property) {
					if (inStr.equals(midStr))
						isIn = true;
					break;
				}
				if (isIn == false) {
					property.add(midStr);
				}
			}

		} catch (Exception e) {

		}
		return property;
	}
}
