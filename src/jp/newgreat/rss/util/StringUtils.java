package jp.newgreat.rss.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	public static String ellipse(String strArg, int intArg){
		String rtn = null;
		if ( strArg==null
		 ||( strArg!=null&&(strArg.length()-intArg)<=0 )
		 ||( strArg!=null&&(intArg-3)<=0)
		 ||( strArg.length()<=intArg)){
			rtn = strArg;
		}else{
			rtn = strArg.substring(0, intArg-3).concat("...");
		}
		return rtn;
	}
	public static String sanitizeNClean(String arg){
		String rtn = null;
		if ( arg != null || !"".equals(arg))
			rtn = arg.trim().replaceAll("<.+?>", "");
		return rtn;
	}
	public static String removeLineField(String arg){
		String rtn = null;
		if ( arg != null || !"".equals(arg))
			rtn = arg.replaceAll("\n", "");
		return rtn;
	}
	public static String getTimestampString(){
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.Format
				.yyyyMMddTHHmmssSSSZ);
		String yyyyMMddTHHmmss_SSSZ = sdf.format(new Date());
		return yyyyMMddTHHmmss_SSSZ;
	}
}
