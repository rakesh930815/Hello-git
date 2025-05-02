package utilities;

import java.util.Date;

public class CommonUtils {
	
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=150;
	public static final int EXPLICIT_WAIT_BASIC_TIME=300;
	
	public String getEmailWithTimeStamp() {
		
		Date date = new Date();
		return "amotoori"+date.toString().replace(" ","_").replace(":","_")+"@gmail.com";
		
	}

}
