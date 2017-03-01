package simpleDateFormat;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/*
	 * 一旦在生产环境中一定负载情况下时，这个问题就出来了。他会出现各种不同的情况，比如转化的时间不正确，比如报错，比如线程被挂死等等。
	 * private static final  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    public static  String formatDate(Date date)throws ParseException{
        return sdf.format(date);
    }
    
    public static Date parse(String strDate) throws ParseException{

        return sdf.parse(strDate);
    }*/
	
	/*
	 * 在需要用到SimpleDateFormat 的地方新建一个实例，不管什么时候，将有线程安全问题的对象由共享变为局部私有都能避免多线程问题，不过也加重了创建对象的负担。在一般情况下，这样其实对性能影响比不是很明显的。
	 * public static  String formatDate(Date date)throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return sdf.format(date);
   }
   
   public static Date parse(String strDate) throws ParseException{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
       return sdf.parse(strDate);
   }*/
	
	/*
	 * 当线程较多时，当一个线程调用该方法时，其他想要调用此方法的线程就要block，多线程并发量大的时候会对性能有一定的影响。
	 * private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      
    public static String formatDate(Date date)throws ParseException{
        synchronized(sdf){
            return sdf.format(date);
        }  
    }
    
    public static Date parse(String strDate) throws ParseException{
        synchronized(sdf){
            return sdf.parse(strDate);
        }
    }  */
	
	/*
	 * 使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法。
	 *  private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    public static Date parse(String dateStr) throws ParseException {
        return threadLocal.get().parse(dateStr);
    }

    public static String format(Date date) {
        return threadLocal.get().format(date);
    }*/
	//另一种写法
	//使用ThreadLocal, 也是将共享变量变为独享，线程独享肯定能比方法独享在并发环境中能减少不少创建对象的开销。如果对性能要求比较高的情况下，一般推荐使用这种方法。
	private static final String date_format = "yyyy-MM-dd HH:mm:ss";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(); 
 
    public static DateFormat getDateFormat()   
    {  
        DateFormat df = threadLocal.get();  
        if(df==null){  
            df = new SimpleDateFormat(date_format);  
            threadLocal.set(df);  
        }  
        return df;  
    }  

    public static String formatDate(Date date) throws ParseException {
        return getDateFormat().format(date);
    }

    public static Date parse(String strDate) throws ParseException {
        return getDateFormat().parse(strDate);
    }   
}
