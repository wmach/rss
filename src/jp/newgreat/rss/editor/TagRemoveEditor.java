package jp.newgreat.rss.editor;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.Constants.Fields;
import jp.newgreat.rss.util.StringUtils;

public class TagRemoveEditor extends BaseEditor implements AccepteeIF{
	public TagRemoveEditor(RssIF rss){
		super(rss);
	}
	@Override protected Item edit(Item item, Fields fieldsArg){
		if ( fieldsArg == Fields.ITEM_TITLE){
			String title = StringUtils.sanitizeNClean(item.title);
			item.title = title;
		}
		if ( fieldsArg == Fields.ITEM_DESCRIPTION){
			String desc =StringUtils.sanitizeNClean(item.description);
			item.description = desc;
		}
		return item;
	}
	@Override protected Rss10.Item edit(Rss10.Item item, Fields fieldsArg){
		if ( fieldsArg == Fields.ITEM_TITLE){
			String title = StringUtils.sanitizeNClean(item.title);
			item.title = title;
		}
		if ( fieldsArg == Fields.ITEM_DESCRIPTION){
			System.err.println("item=="+item.description);
			String desc =StringUtils.sanitizeNClean(item.description);
			item.description = desc;
		}
		return item;
	}
	public AccepteeIF accept(LogicVisitor v) {
		EllipsizeEditor rtn = (EllipsizeEditor) v.visit( this );
		return rtn;
	}
	@Override
	protected Item edit(Item itemArg, Fields fieldsArg, int sizeArg) {
		return itemArg;
	}
	@Override
	protected Rss10.Item edit(Rss10.Item itemArg, Fields fieldsArg, int sizeArg) {
		return itemArg;
	}
}//Unreachable