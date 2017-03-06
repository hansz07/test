package githubtest;

import java.io.UnsupportedEncodingException;

public class EncodingTest {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String str = "你好hello";  
        int byte_len = str.getBytes("utf-8").length;  
        int len = str.length();  
        System.out.println("字节长度为：" + byte_len);  
	    System.out.println("字符长度为：" + len);  
	    System.out.println("系统默认编码方式：" + System.getProperty("file.encoding"));  
	}

}
