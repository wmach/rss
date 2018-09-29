package jp.newgreat.rss.result;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.LogUtils;

public class FileResult extends BaseResult implements ResultIF{
	public FileResult(RssIF rssIF){
		super(rssIF);
		File f = new File("./res/rss.summary.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(f);
		} catch (IOException e) {
			LogUtils.e(e);
		}
		PrintWriter pw =new PrintWriter(new BufferedWriter(fw));
		pw.close();
		try{
			fw.close();
		}catch(IOException e){
			LogUtils.e( e );
		}
	}
	@Override protected void doOutput(String lineArg){
		File f = new File("./res/rss.summary.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			LogUtils.e(e);
		}
		PrintWriter pw =new PrintWriter(new BufferedWriter(fw));
		pw.println( lineArg );
		pw.close();
		try{
			fw.close();
		}catch(IOException e){
			LogUtils.e( e );
		}
	}
	@Override
	public AccepteeIF accept(LogicVisitor v) {
		TerminalAcceptee rtn = (TerminalAcceptee)v.visit( this );
		return rtn;
	}
	@Override
	public RssIF doOutput(RssIF rssIFArg) {
		return null;
	}
	@Override
	public HtmlIF doOutput(HtmlIF htmlIFArg) {
		return null;
	}
}//Unreachable