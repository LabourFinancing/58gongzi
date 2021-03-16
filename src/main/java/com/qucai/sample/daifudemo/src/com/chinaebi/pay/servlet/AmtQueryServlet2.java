package com.qucai.sample.daifudemo.src.com.chinaebi.pay.servlet;

import javax.servlet.http.HttpServlet;



public class AmtQueryServlet2 extends HttpServlet {

	private static final long serialVersionUID = 2809334177724187609L;

	public static void main(String[] args) {
		String chinese = "牟AA";
		if (chinese.matches("[\u4E00-\u9FA5]+")) {
			System.out.println("我是中文");
		} else {
			System.out.println("我不是中文");
		}

	}

}
