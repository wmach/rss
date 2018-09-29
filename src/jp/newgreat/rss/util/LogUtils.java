package jp.newgreat.rss.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jp.newgreat.rss.models.Html;
import jp.newgreat.rss.models.Html.Anchor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.util.Constants.Mode;

public class LogUtils {
	private static Mode mode = Mode.STDERR;
	public static void setMode(Mode modeArg){
		mode = modeArg;
	}
	public static void d(String arg){
		if ( mode == Mode.STDOUT){
			System.out.println( StringUtils.getTimestampString()+arg );
		}
		else if ( mode == Mode.FILE ){
			File f = new File("./logs/debug.log");
			FileWriter fw = null;
			PrintWriter pw = null;
			try{
				fw = new FileWriter(f, true);
				pw = new PrintWriter(new BufferedWriter(fw));
				pw.println( StringUtils.getTimestampString() +arg );
			} catch (IOException e){
			}
			pw.close();}
	}
	public static void d(HtmlIF hArg){
		System.err.println(getElement((Html)hArg));
		if ( mode == Mode.STDOUT){
			System.out.println( getElement( (Html)hArg ));
		}
		else if ( mode == Mode.FILE){
			File f = new File("./logs/debug.log");
			FileWriter fw = null;
			PrintWriter pw = null;
			try{
				fw = new FileWriter(f, true);
				pw = new PrintWriter(new BufferedWriter(fw));
				pw.println( getElement( (Html)hArg ));
			} catch (IOException e){
			}
			pw.close();}
	}
	public static void d(Rss10 rArg){
		if ( mode == Mode.STDOUT){
			System.out.println( getElement( rArg ));
		}
		else if ( mode == Mode.FILE){
			File f = new File("./logs/debug.log");
			FileWriter fw = null;
			PrintWriter pw = null;
			try{
				fw = new FileWriter(f, true);
				pw = new PrintWriter(new BufferedWriter(fw));
				pw.println( getElement( rArg ));
			} catch (IOException e){
			}
			pw.close();}
	}
	public static void d(Rss20 rArg){
		if ( mode == Mode.STDOUT){
			System.out.println( getElement( rArg ));
		}
		else if ( mode == Mode.FILE){
			File f = new File("./logs/debug.log");
			FileWriter fw = null;
			PrintWriter pw = null;
			try{
				fw = new FileWriter(f, true);
				pw = new PrintWriter(new BufferedWriter(fw));
				pw.println( getElement( rArg ));
			} catch (IOException e){
			}
			pw.close();}
	}
	public static void e(Exception eArg){
		if ( mode == Mode.STDOUT){
			for (StackTraceElement elm : eArg.getStackTrace()){
				System.out.println(getStackTrace(elm));}}
		else if ( mode == Mode.FILE){
			File f = new File("./logs/error.log");
			FileWriter fw = null;
			PrintWriter pw = null;
			try {
				fw = new FileWriter(f, true);
				pw =new PrintWriter(new BufferedWriter(fw));
				for (StackTraceElement elm : eArg.getStackTrace()){
					pw.println( getStackTrace(elm));}
			} catch (IOException e) {
			}
			pw.close();}
		else {
			for (StackTraceElement elm : eArg.getStackTrace()){
				System.err.println(getStackTrace(elm));}}
	}
	private static String getElement(Html h){
		String rtn = StringUtils.getTimestampString();
		if ( h==null || h.body==null || (h.body!=null&&h.body.anchors==null)){
			return rtn;
		}
		for ( Anchor elm : h.body.anchors){
			rtn += String.format("[anchor.href]%s", elm.link);
			rtn += String.format("[anchor]%s", elm.content);
		}
		return rtn;
	}
	private static String getElement(Rss10 r){
		String rtn = StringUtils.getTimestampString();
		rtn = rtn+String.format("\n\t[channel.title]%s", r.channel.title);
		rtn = rtn+String.format("\n\t[channel.description]%s"
				, r.channel.description);
		int i=0;
		if ( r.items != null )
		{
		for ( Rss10.Item elm : r.items ){
			i++;
			rtn = rtn+String.format("\n\t[item[%d].title] %s"
					, i, elm.title);
			String desc = elm.description;
			desc = StringUtils.sanitizeNClean( desc );
			desc = StringUtils.removeLineField( desc );
			desc = StringUtils.ellipse(desc, 100);
			rtn = rtn+String.format("\n\t[item[%d].description]%s"
					, i, desc);}
		}
		return rtn;
	}
	private static String getElement(Rss20 r){
		String rtn = StringUtils.getTimestampString();
		rtn = rtn+String.format("\n\t[channel.title]%s", r.channel.title);
		rtn = rtn+String.format("\n\t[channel.description]%s"
				, r.channel.description);
		int i=0;
		for ( Item elm : r.channel.items ){
			i++;
			rtn = rtn+String.format("\n\t[channel.item[%d].title] %s"
					, i, elm.title);
			String desc = elm.description;
			desc = StringUtils.sanitizeNClean( desc );
			desc = StringUtils.removeLineField( desc );
			desc = StringUtils.ellipse(desc, 100);
			rtn = rtn+String.format("\n\t[channel.item[%d].description]%s"
					, i, desc);}
		return rtn;
	}
	private static String getStackTrace(StackTraceElement arg){
		String t = StringUtils.getTimestampString();
		String rtn = String.format("[%s] %s.%s(%s:%s)"
				, t
				, arg.getClassName()
				, arg.getMethodName()
				, arg.getFileName()
				, String.valueOf(arg.getLineNumber())
				);
		return rtn;
	}
}