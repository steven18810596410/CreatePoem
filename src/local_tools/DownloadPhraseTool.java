package local_tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import http_tools.HttpRequest;

public class DownloadPhraseTool {

	public static void main(String[] args) {
		Statement stmt = null;
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager
					.getConnection("jdbc:mysql://127.0.0.1:3306/words_db?user=root&password=a111111");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int startUnic = 97;// a
		int endUnic = 122;// z

		int currentUnic = startUnic;
		while (currentUnic <= endUnic) {

			int currentPageIndex = 1;
			boolean hasNext = true;

			while (hasNext) {
				try {
					String url = "http://tool.xdf.cn/ch/py_"
							+ (char) currentUnic + "_" + currentPageIndex
							+ ".html";
					String page = HttpRequest.sendGet(url, null, "utf-8");
					hasNext = page.indexOf("target=\"_self\">下一页") != -1;

					String listStr = page.substring(page.indexOf("asd\">") + 5,
							page.indexOf("<p class=\"page\">"));

					Pattern p = Pattern.compile("(	[\u4E00-\u9FA5]*\\[拼音\\])");
					Matcher m = p.matcher(listStr);
					while (m.find()) {
						String name = m.group();
						name = name.substring(1, name.indexOf("["));

						String tmpStr = listStr
								.substring(listStr.indexOf(name));
						tmpStr = tmpStr.substring(
								tmpStr.indexOf(name) + name.length(),
								tmpStr.indexOf("html") + 4);
						String phraseUrl = tmpStr.substring(tmpStr
								.indexOf("href=\"") + 6);

						String analyPage = HttpRequest
								.sendGet("http://tool.xdf.cn" + phraseUrl,
										null, "utf-8");
						if (analyPage.indexOf("[释义]：") != -1) {
							tmpStr = analyPage.substring(analyPage
									.indexOf("[释义]："));
							String analy = tmpStr.substring(
									tmpStr.indexOf("[释义]：") + 5,
									tmpStr.indexOf("</dd>"));
							
							stmt.execute("insert into phrases(name,url,analy) values('"
									+ name + "','" + phraseUrl + "','" + analy + "')");
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				}
				currentPageIndex++;
			}

			currentUnic++;
		}
		
		try {
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
