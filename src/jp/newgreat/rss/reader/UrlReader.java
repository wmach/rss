package jp.newgreat.rss.reader;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.util.LogUtils;

public class UrlReader implements ReaderIF, AccepteeIF{
	private String url = null;
	public String getUrlString(){
		return url;
	}
	public UrlReader(String arg){
		url = arg;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		AccepteeIF rtn = null;
		rtn = v.visit( this );
		return rtn;
	}
	@Override
	public InputStream getInputStream() {
		InputStream rtn = null;
		try {
			URL url = new URL( this.url );
			URLConnection conn = url.openConnection();
			rtn = conn.getInputStream();
		} catch (MalformedURLException e){
			LogUtils.e(e);
		} catch (IOException e){
			LogUtils.e(e);
		}
		return rtn;
	}
}