package com.zcc.login.common.utils;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by stephan on 04.07.17.
 */
@Component
public class DateUtil implements Serializable {

    private static final long serialVersionUID = -3301695478208950415L;
    private static final String timePattern = "yyyy-MM-dd HH:mm:ss";
    private static final Logger LOGGER= LoggerFactory.getLogger(DateUtil.class);
    public Date now() {
        return new Date();
    }

    public Date getDateFromYmdt(String ymdt){
        SimpleDateFormat format = new SimpleDateFormat(timePattern);
        Date date = null;
        try{
            date = format.parse(ymdt);
        }catch (ParseException e) {
            LOGGER.error("TimeStamp format error: " + e.getMessage());
        }
        return date;
    }
}
