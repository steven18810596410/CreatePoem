package local_tools;

import http_tools.IdentifyPhraseTool;

import java.util.ArrayList;

import bean.WordTP;

public class MakePhraseTool {

	public static String makePhrase(ArrayList<WordTP> wordtpList) {
		String phrase = "";

		boolean isPhrase = false;

		while (!isPhrase) {
			phrase = "";
			for (WordTP wtp : wordtpList) {
				phrase += DatabaseTool.getWordByToneAndProperty(wtp.toneIndex,
						wtp.property);
			}
			isPhrase = IdentifyPhraseTool.identifyPhrase(phrase);
			System.out.println(phrase+" "+isPhrase);
		}
		return phrase;
	}

}
