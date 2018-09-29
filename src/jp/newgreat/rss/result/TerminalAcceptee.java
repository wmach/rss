package jp.newgreat.rss.result;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.RssIF;

public class TerminalAcceptee implements ResultIF {
	public TerminalAcceptee(ResultIF result){}
	@Override public AccepteeIF accept(LogicVisitor v) {
		return null;
	}
	@Override public RssIF doOutput(RssIF rssIFArg) {
		return null;
	}
	@Override public HtmlIF doOutput(HtmlIF htmlIFArg) {
		return null;
	}
}
