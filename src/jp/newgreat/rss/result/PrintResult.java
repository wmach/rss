package jp.newgreat.rss.result;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.RssIF;

public class PrintResult extends BaseResult implements ResultIF{
	public PrintResult(HtmlIF html){
		super( html );
	}
	public PrintResult(RssIF rss){
		super(rss);
	}
	@Override protected void doOutput(String lineArg){
		System.out.println( lineArg );
	}
	@Override public HtmlIF doOutput(HtmlIF htmlIFArg){
		return null;
	}
	@Override
	public AccepteeIF accept(LogicVisitor v) {
		FileResult rtn = (FileResult) v.visit( this );
		return rtn;
	}
	@Override
	public RssIF doOutput(RssIF rssIFArg) {
		return null;
	}
}
