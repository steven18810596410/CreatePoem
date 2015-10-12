package main;

import http_tools.IdentifyPhraseTool;

import java.io.FileWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import bean.WordTP;
import local_tools.DatabaseTool;
import local_tools.MakePhraseTool;

public class Main {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 昔人已乘黄鹤去，此地空余黄鹤楼
		// 1232244，3412242
		// (形名)(副)(动)(形名)(动),(代名)(副动)(形名名)
		// 黄鹤=黄鹤

		String rs = "";

		ArrayList<WordTP> wordTPs = new ArrayList<>();
		wordTPs.add(new WordTP(1, "名"));
		wordTPs.add(new WordTP(2, "名"));
		rs += MakePhraseTool.makePhrase(wordTPs);
		write(rs);

		rs += DatabaseTool.getWordByToneAndProperty(3, "副");
		write(rs);
		rs += DatabaseTool.getWordByToneAndProperty(2, "动");
		write(rs);

		wordTPs = new ArrayList<>();
		wordTPs.add(new WordTP(2, "形"));
		wordTPs.add(new WordTP(4, "名"));
		wordTPs.add(new WordTP(2, "名"));
		String eq = MakePhraseTool.makePhrase(wordTPs);
		rs += eq.subSequence(0, 2);
		write(rs);

		rs += DatabaseTool.getWordByToneAndProperty(4, "动");
		write(rs);

		rs += ",";

		wordTPs = new ArrayList<>();
		wordTPs.add(new WordTP(3, "代"));
		wordTPs.add(new WordTP(4, "名"));
		rs += MakePhraseTool.makePhrase(wordTPs);
		write(rs);

		wordTPs = new ArrayList<>();
		wordTPs.add(new WordTP(1, "副"));
		wordTPs.add(new WordTP(2, "动"));
		rs += MakePhraseTool.makePhrase(wordTPs);
		write(rs);

		rs += eq;
		write(rs);
	}

	public static void write(String rs) {
		try {
			FileWriter fileWriter = new FileWriter(
					"/Users/steven/Desktop/poem.txt");
			fileWriter.write(rs);
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {

		}
	}
}
