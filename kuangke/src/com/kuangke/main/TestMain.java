package com.kuangke.main;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.aspectj.weaver.GeneratedReferenceTypeDelegate;

public class TestMain {

	public static void main(String[] args) {
		
		Aaaa aaaa = new Aaaa();
		System.out.println(aaaa.getVa());
	}
	
	public static String getType(Object o){ //获取变量类型方法

		return o.getClass().toString(); //使用int类型的getClass()方法

		}

}
