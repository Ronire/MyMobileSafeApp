package com.lv.mymobilesafeapp.view;

import com.lv.mymobilesafeapp.bean.LinkMan;

import java.util.Comparator;


public class PinyinComparator implements Comparator<LinkMan> {

	public int compare(LinkMan o1, LinkMan o2) {
		if (o1.getSortLetters().equals("@")
				|| o2.getSortLetters().equals("#")) {
			return -1;
		} else if (o1.getSortLetters().equals("#")
				|| o2.getSortLetters().equals("@")) {
			return 1;
		} else {
			return o1.getSortLetters().compareTo(o2.getSortLetters());
		}
	}

}
