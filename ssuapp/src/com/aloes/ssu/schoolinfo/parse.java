package com.aloes.ssu.schoolinfo;

import java.util.HashMap;

public class parse {
	private int week = 0;
	// private Pattern var;
	// private final String var = "^[0-9]{2}$";
	// private final String var = "[0-9]{2}[:][0-9]{2}[-][0-9]{2}[:][0-9]{2}";
	// private final String var2 = "^[0-9]{2}[B]?[0-9]{3}$";
	private HashMap<String, String> map = new HashMap<String, String>();

	public class Class {
		public int week;
		public int startHour;
		public int startMinute;
		public float startMinute_f;
		public int endHour;
		public int endMinute;
		public float endMinute_f;
		public String classrome;

	}

	public Class oneClass = new Class();
	public Class twoClass = new Class();

	public parse() {
		map.put("01", "º£");// º£¾îµå
		map.put("02", "°æ");// °æ»ó°ü
		map.put("03", "¹®");// ¹®È­°ü
		map.put("04", "¾È");// ¾ÈÀÍÅÂ
		map.put("05", "Çü");// Çü³²
		map.put("06", "");//
		map.put("07", "¹é");// ¹é¸¶°ü
		map.put("08", "");//
		map.put("09", "");// º¥Ã³Áß¼Ò±â¾÷¼¾ÅÍ
		map.put("10", "º¥");// º¥Ã³Áß¼Ò±â¾÷¼¾ÅÍ
		map.put("11", "Áø");// Áø¸®°ü
		map.put("12", "Á¶");// Á¶¸¸½Ä
		map.put("13", "");//
		map.put("14", "");//
		map.put("15", "");
		map.put("16", "");
		map.put("17", "");
		map.put("18", "");
		map.put("19", "Àü");// Àü»ê°ü
		map.put("20", "¹Ì");// ¹Ì·¡°ü
		map.put("21", "Á¤");// Á¤º¸°úÇĞ°ü
	}

	public void init(String str) {
		String tmp[] = str.split("[-][°¡-ÆR]{2,3}");
		for (int i = 0; i < tmp.length - 1; i++) {
			// System.out.println((int)tmp[i].toCharArray()[0]);
			if (tmp[i].toCharArray()[0] == ')') {
				tmp[i] = tmp[i].substring(1);
			}
			// System.out.println(tmp[i]);
			parsese(tmp[i]);
		}
		if (oneClass.week != 0) {
			// System.out.println("a "+oneClass.classrome.substring(0, 2));
			oneClass.startMinute_f = ((float) oneClass.startMinute / 60) * 100;
			oneClass.endMinute_f = ((float) oneClass.endMinute / 60) * 100;
			oneClass.classrome = map.get(oneClass.classrome.substring(0, 2))
					+ oneClass.classrome.substring(2);
		}
		if (twoClass.week != 0) {
			// System.out.println("a "+twoClass.classrome.substring(0, 2));
			twoClass.startMinute_f = ((float) twoClass.startMinute / 60) * 100;
			twoClass.endMinute_f = ((float) twoClass.endMinute / 60) * 100;
			twoClass.classrome = map.get(twoClass.classrome.substring(0, 2))
					+ twoClass.classrome.substring(2);
		}

	}

	private void parsese(String str) {
		// System.out.println(str);

		char WEEKS[] = { '¿ù', 'È­', '¼ö', '¸ñ', '±İ', 'Åä' };
		String tmp[] = str.split(" ");
		int splitNum = 0;

		for (int i = 0; i < tmp.length; ++i) {
			if (tmp[i].toCharArray()[0] >= 48 && tmp[i].toCharArray()[0] <= 57) {
				splitNum = i;
				i = tmp.length;
			} else {
				// System.out.println("d");
				for (int j = 0; j < WEEKS.length; ++j) {
					if (tmp[i].toCharArray()[0] == WEEKS[j]) {
						if (oneClass.week == 0)
							oneClass.week = j + 1;
						else
							twoClass.week = j + 1;
						week = j + 1;
						j = WEEKS.length;

					}
				}
			}
		}
		if (oneClass.startHour == 0) {
			String startTime[] = tmp[splitNum].split("-")[0].split(":");
			String endTime[] = tmp[splitNum].split("-")[1].split(":");
			oneClass.startHour = Integer.parseInt(startTime[0]);
			oneClass.startMinute = Integer.parseInt(startTime[1]);
			oneClass.endHour = Integer.parseInt(endTime[0]);
			oneClass.endMinute = Integer.parseInt(endTime[1]);

			oneClass.classrome = tmp[splitNum + 2].split("-")[0];
		}
		if (oneClass.week == week) {
			String endTime[] = tmp[splitNum].split("-")[1].split(":");
			oneClass.endHour = Integer.parseInt(endTime[0]);
			oneClass.endMinute = Integer.parseInt(endTime[1]);
		}
		// System.out.println("a"+week);
		if (twoClass.week == week) {
			// System.out.println("a");
			if (twoClass.startHour == 0) {
				String startTime[] = tmp[splitNum].split("-")[0].split(":");
				twoClass.startHour = Integer.parseInt(startTime[0]);
				twoClass.startMinute = Integer.parseInt(startTime[1]);
				twoClass.classrome = tmp[splitNum + 2].split("-")[0];
			}
			String endTime[] = tmp[splitNum].split("-")[1].split(":");
			twoClass.endHour = Integer.parseInt(endTime[0]);
			twoClass.endMinute = Integer.parseInt(endTime[1]);
		}

	}

}