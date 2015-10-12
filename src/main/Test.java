package main;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {

		ArrayList<String> list1 = new ArrayList<>();
		ArrayList<String> list2 = new ArrayList<>();
		ArrayList<String> list3 = new ArrayList<>();

		list1.add("1");
		list1.add("2");
		list1.add("3");

		list2.add("a");
		list2.add("b");
		list2.add("d");

		list3.add("A");
		list3.add("B");
		list3.add("C");

		ArrayList<String> aaaaa = new ArrayList<>();
		
		for (String str1 : list1) {
			for (String str2 : list2) {
				for (String str3 : list3) {
					String tmp = str1 + str2 + str3;
					aaaaa.add(tmp);
				}
			}
		}
		
		System.out.println(aaaaa.toString());
	}
}
