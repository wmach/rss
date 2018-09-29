package jp.newgreat.rss.editor;

import java.util.List;
import java.util.Map;

import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20;
import jp.newgreat.rss.models.Rss20.Channel;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.Constants.Fields;

public abstract class BaseEditor implements EditorIF{
	protected RssIF rssIF = null;
	public BaseEditor(RssIF rss){
		this.rssIF = rss;
	}
	abstract protected Item edit(Item itemArg, Fields fieldsArg);
	abstract protected Item edit(Item itemArg, Fields fieldsArg, int sizeArg);
	abstract protected Rss10.Item edit(Rss10.Item itemArg, Fields fieldsArg);
	abstract protected Rss10.Item edit(Rss10.Item itemArg, Fields fieldsArg, int sizeArg);
	@Override public RssIF edit(List<Fields> listArg){
		if ( rssIF instanceof Rss20){
			Rss20 rss20 = (Rss20)rssIF;
			Channel channel = rss20.channel;
			for ( Fields field : listArg ){
				for ( Item elm : channel.getItems()){
					elm = edit(elm, field);
				}
			}
		}
		return rssIF;
	}
	@Override public RssIF edit(Map<Fields,String> mapArg){
		if ( rssIF instanceof Rss20){
			Rss20 rss20 = (Rss20)rssIF;
			Channel channel = (Channel)rss20.channel;
			for ( Map.Entry<Fields, String> ent : mapArg.entrySet()){
				for ( Item elm : channel.getItems()){
					int size = Integer.parseInt(ent.getValue());
					elm = edit(elm, ent.getKey(), size);
				}
			}
		}
		if ( rssIF instanceof Rss10){
			Rss10 rss10 = (Rss10)rssIF;
			for ( Map.Entry<Fields, String> ent : mapArg.entrySet()){
				for ( Rss10.Item elm : rss10.items){
					int size = Integer.parseInt(ent.getValue());
					elm = edit(elm, ent.getKey(), size);
				}
			}
		}
		return rssIF;
	}
}
