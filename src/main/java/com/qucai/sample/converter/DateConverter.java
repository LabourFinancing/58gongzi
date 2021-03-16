package com.qucai.sample.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.convert.converter.Converter;

public class DateConverter implements Converter<String, Date> {

	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");

	@Override
	public Date convert(String source) {
		if (StringUtils.isNotBlank(source)) {
			// yyyy-MM-dd
			String regDate1 = "^\\d{4}-\\d{2}-\\d{2}$";
			// yyyy-MM
			String regDate2 = "^\\d{4}-\\d{2}$";

			Pattern p1 = Pattern.compile(regDate1);
			Pattern p2 = Pattern.compile(regDate2);

			if (p1.matcher(source).matches()) {
				try {
					return sdf1.parse(source);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (p2.matcher(source).matches()) {
				try {
					return sdf2.parse(source);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				return null;
			}
		}
		return null;
	}
}