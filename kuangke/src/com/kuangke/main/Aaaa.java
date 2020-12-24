package com.kuangke.main;

public class Aaaa {
	public String getVa(){
		try {
		    int i= 1/0;
		    System.out.println(i);
		    return "11111";
		} catch (Exception e) {
			return "22222";
		} finally {
			return "33333";
		}
	}
}
