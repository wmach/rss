package jp.newgreat.rss.editor;

import java.util.List;
import java.util.Map;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.Constants.Fields;

public interface EditorIF extends AccepteeIF {
	public RssIF edit(Map<Fields,String> mapArg);
	public RssIF edit(List<Fields> listArg);
}
