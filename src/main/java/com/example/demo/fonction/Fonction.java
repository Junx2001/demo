package com.example.demo.fonction;

import java.util.regex.Pattern;

public class Fonction {
	public static boolean verifEmail(String email) {
		if(email.contains("@")) {
			String [] arobase = email.split("@");
			if(arobase[0].length()>0 && arobase[1].length()>0) {
				if (arobase[1].contains(".")) {
					String[] point = arobase[1].split("\\.");
					if (point[0].length()>0 && point[1].length()>0) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean verifMdp(String mdp) {
		if(mdp.length()>=8 && !Pattern.matches(".*[êéèàùîïäëüöôâû].*", mdp)) {
			return true;
		}
		return false;
	}
}
