package jp.newgreat.rss.parser;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.models.HtmlIF;

public interface HtmlParserIF extends AccepteeIF {
	public HtmlIF parse();
}
