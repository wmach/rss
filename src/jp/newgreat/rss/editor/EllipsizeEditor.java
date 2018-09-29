package jp.newgreat.rss.editor;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.result.PrintResult;
import jp.newgreat.rss.util.Constants.Fields;
import jp.newgreat.rss.util.StringUtils;

public class EllipsizeEditor extends BaseEditor implements EditorIF{
	public EllipsizeEditor(RssIF rss){
		super(rss);
	}
	@Override protected Item edit(Item itemArg, Fields fieldsArg){
		return itemArg;
	}
	@Override
	protected Rss10.Item edit(Rss10.Item itemArg, Fields fieldsArg) {
		return itemArg;
	}
	@Override protected Item edit(Item itemArg, Fields fieldsArg, int sizeArg){
		if ( fieldsArg == Fields.ITEM_TITLE){
			String title = StringUtils.ellipse(itemArg.getTitle()
					, sizeArg);
			itemArg.setTitle( title );
		}
		if ( fieldsArg == Fields.ITEM_DESCRIPTION){
			String desc = StringUtils.ellipse(itemArg
					.getDescription(), sizeArg);
			itemArg.setDescription( desc );
		}
		return itemArg;
	}
	protected Rss10.Item edit(Rss10.Item itemArg, Fields fieldsArg, int sizeArg){
		if ( fieldsArg == Fields.ITEM_TITLE){
			String title = StringUtils.ellipse(itemArg.title, sizeArg);
			itemArg.title = title;
		}
		if ( fieldsArg == Fields.ITEM_DESCRIPTION){
			String desc = StringUtils.ellipse(itemArg.description, sizeArg);
			itemArg.description = desc;
		}
		return itemArg;
	}
	@Override
	public AccepteeIF accept(LogicVisitor v) {
		PrintResult rtn = (PrintResult) v.visit( this );
		return rtn;
	}
}