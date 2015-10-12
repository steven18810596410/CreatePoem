package http_tools;

import java.util.ArrayList;

/**
 * 获取中文字的音节
 * @author steven
 *
 */
public class IdentifyToneTool {

	public static final String[] tones = { "ˉ", "ˊ", "ˇ", "ˋ" };

	public static final String api = "http://www.chazidian.com/r_zi_zd";

	/**
	 * 识别文字的音节
	 * @param unic 文字的unicode
	 * @return 该文字的所有音节
	 */
	public static ArrayList<String> getTones(String unic) {
		ArrayList<String> ts = new ArrayList<>();
		
		String url = api + unic;
		
		String htmlStr = HttpRequest.sendGet(url, "",null);
		
		for (int i = 0; i < 4; i++) {
			if(htmlStr.equals(HttpRequest.NETERROR)){
				return null;
			}
			if (htmlStr.indexOf(tones[i]) != -1) {
				ts.add(tones[i]);
			}			
		}

		return ts;
	}
}
