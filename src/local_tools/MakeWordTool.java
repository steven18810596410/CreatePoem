package local_tools;

/**
 * 生成中文字
 * @author steven
 *
 */

public class MakeWordTool {

	public static int makeWordUnic() {
		int unic = (int) (Math.random() * (40869 - 19968) + 19968);
		return unic;
	}

	public static String unic2unic16(int unic) {
		return Integer.toHexString(unic);
	}

	public static char getWordByUnic(int unic) {
		return ((char) unic);
	}
}
