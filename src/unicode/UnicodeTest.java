package unicode;

import java.io.UnsupportedEncodingException;

public class UnicodeTest {

	public static void main(String[] args) throws UnsupportedEncodingException{
		unicode();	
//		System.out.println(getUnicode("衡水市"));
//		String uString = "\\uff88";
//		System.out.println(decodeUnicode(uString));
	}
	public static String getUnicode(String source){
		 String returnUniCode=null;
		 String uniCodeTemp=null;
		 for(int i=0;i<source.length();i++){
		  uniCodeTemp = "\\u"+Integer.toHexString((int)source.charAt(i));//使用char类的charAt()的方法
		  returnUniCode=returnUniCode==null?uniCodeTemp:returnUniCode+uniCodeTemp;
		 }
		 System.out.print(source +" 's unicode = "+returnUniCode);
		 return returnUniCode;//返回一个字符的unicode的编码值
	}
	public static void unicode() {
		for (int i = 0; i < (1 << 16); i++) {
			String hexString = Integer.toHexString(i);
			String tempString = hexString;
			if (i == 0) {
				tempString = "0000";
			}else if (hexString.length() < 4) {
				for (int j = 0; j < (4 - hexString.length()); j++) {
					tempString = "0" + tempString;
				}
			}
			String unicodeString = "\\u"+tempString;
			System.out.print(decodeUnicode(unicodeString));
			System.out.print("\t");
			if (i % 10 == 0) {
				System.out.println();
			}
		}
	}
	
	public static String decodeUnicode(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}     
	
}
