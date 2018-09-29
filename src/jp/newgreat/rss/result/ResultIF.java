package jp.newgreat.rss.result;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.RssIF;

public interface ResultIF extends AccepteeIF {
	public RssIF doOutput(RssIF rssIFArg);
	public HtmlIF doOutput(HtmlIF htmlIFArg);
}
