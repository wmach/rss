package jp.newgreat.rss.editor;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.Html;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.result.PrintResult;

public class LinkEditor implements HtmlEditorIF {
	private HtmlIF html;
	public LinkEditor(HtmlIF arg) {
		this.html = arg;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		PrintResult rtn = (PrintResult) v.visit( this );
		return rtn;
	}
	@Override public Html edit() {
		Html rtn = (Html)this.html;
		return rtn;
	}
}//Unreachable