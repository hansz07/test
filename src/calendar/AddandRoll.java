package calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** 
 * Calendar类roll和add的区别. 
 */ 
public class AddandRoll {

	public static void main(String[] args) {  
        System.out.println("当前时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));  
        Calendar first = Calendar.getInstance();  
        Calendar second = Calendar.getInstance();  
        System.out.println(first.equals(second));  
        /* 
         * roll方法只是对相应时间属性的域内做变化 
         * 例如,对月份使用roll方法，它会在1-12的范围内变化，不会影响的年 
         * 2011-1-15 roll(Calendar.MONTH, -1) 后是2011-12-15 
         */  
        first.roll(Calendar.MONTH, -1);  
        System.out.println("roll后时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(first.getTime()));  
        /* 
         * add方法会产生其他相关时间属性的连动变化 
         * 2011-1-15 roll(Calendar.MONTH, -1) 后是2011-12-15 
         */  
        second.add(Calendar.MONTH, -1);  
        System.out.println("add后时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(second.getTime()));  
        
        System.out.println("getYearLast:"+getYearLast(2017));
    }  
	
	public static Date getYearLast(int year) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        System.out.println(sf.format(calendar.getTime()));
        calendar.clear();
        System.out.println(sf.format(calendar.getTime()));
        calendar.set(Calendar.YEAR, year);
        System.out.println(sf.format(calendar.getTime()));
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        System.out.println(sf.format(calendar.getTime()));
        Date currYearLast = calendar.getTime();
        return currYearLast;
    }
}
