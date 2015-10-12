package local_tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import http_tools.IdentifyPropertyTool;
import http_tools.IdentifyToneTool;

public class DownloadWordTool {

	public static void main(String[] args) {		
		String path = "/Users/steven/Desktop/CurrentIndex.txt";

		int currentIndex = readCurrentIndex(path);

		int startIndex = 19968;
		int endIndex = 40869;

		if (currentIndex > startIndex) {
			startIndex = currentIndex;
		}

		while (startIndex <= endIndex) {
			String unic16 = MakeWordTool.unic2unic16(startIndex);

			String word = MakeWordTool.getWordByUnic(startIndex) + "";

			ArrayList<String> toneList = IdentifyToneTool.getTones(MakeWordTool
					.unic2unic16(startIndex));
			if (toneList == null) {
				System.out.println("网络异常，请重新执行");
				return;
			}
			String tone = "";
			if (!toneList.isEmpty()) {
				tone = toneList.toString();
			}

			ArrayList<String> propertyList = IdentifyPropertyTool
					.identifyProperty2(word + "");
			if (propertyList == null) {
				System.out.println("网络异常，请重新执行");
				return;
			}
			String property = "";
			if (!propertyList.isEmpty()) {
				property = propertyList.toString();
			}

			System.out.println(startIndex + " " + word + " " + tone + " "
					+ property);
			if (tone != "" && property != "") {
				DatabaseTool.insert(word, tone, property);
			}
			startIndex++;
			writeCurrentIndex(path, startIndex);
		}
	}

	public static int readCurrentIndex(String path) {
		int currentIndex = 0;
		try {
			String encoding = "utf-8";
			File file = new File(path);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				if ((lineTxt = bufferedReader.readLine()) != null) {
					currentIndex = Integer.parseInt(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		return currentIndex;
	}

	public static void writeCurrentIndex(String path, int currentIndex) {
		try {
			FileWriter fileWriter = new FileWriter(path);
			fileWriter.write(currentIndex + "");
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {

		}
	}
}
