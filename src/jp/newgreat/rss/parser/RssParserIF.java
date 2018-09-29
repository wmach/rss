package jp.newgreat.rss.parser;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.models.RssIF;

public interface RssParserIF extends AccepteeIF{
	public RssIF parse();
}
