/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Asus
 */
public class DateHelper{
    static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public static Date toDate(String date, String...pattern){
        try {
            if(pattern.length > 0){
                DATE_FORMAT.applyPattern(pattern[0]);
            } 
            if(date == null){
                return DateHelper.now();
            }
            return DATE_FORMAT.parse(date);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String toString(Date date, String...pattern){
        if(pattern.length > 0){
            DATE_FORMAT.applyPattern(pattern[0]);
        }
        if(date == null){
            date = DateHelper.now();
        }
        return DATE_FORMAT.format(date);
    }
    
    public static Date now(){
        return new Date();
    }
    
    public static Date addDays(Date date,int days){
        date.setTime(date.getTime() + days*24*60*60*1000);
        return  date;
    }
    
    public static Date add(int days){
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days*24*60*60*1000);
        return  now;
    }
    
    public static boolean isDateValid(String date) 
    {
        String DATEFORMAT = "MM/dd/yyyy";
            try {
                DateFormat df = new SimpleDateFormat(DATEFORMAT);
                df.setLenient(false);
                df.parse(date);
                return true;
            } catch (Exception e) {
                return false;
            }
    }
}
