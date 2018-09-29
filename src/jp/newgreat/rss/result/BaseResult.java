package jp.newgreat.rss.result;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import jp.newgreat.rss.models.Html;
import jp.newgreat.rss.models.Html.Anchor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20;
import jp.newgreat.rss.models.Rss20.Channel;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.StringUtils;

public abstract class BaseResult implements ResultIF {
	private RssIF rssIF= null;
	private HtmlIF htmlIF = null;
	public BaseResult(HtmlIF htmlIF){
		this.htmlIF = htmlIF;
	}
	public BaseResult(RssIF rssIF){
		this.rssIF = rssIF;
	}
	abstract protected void doOutput(String lineArg);
	protected String[] getOutput(HtmlIF htmlArg){
		ArrayList<String> rtnList = new ArrayList<String>();
		Html html = (Html)htmlArg;
		List<Anchor> anchors = html.body.anchors;
		int i=0;
		for ( Anchor elm : anchors ){
			rtnList.add(String.format("Anchor[%d].link]", i, elm.link));
			rtnList.add(String.format("Anchor[%d].tag]", i, elm.content));
			i++;}
		String[] rtn = rtnList.toArray(new String[0]);
		return rtn;
	}
	protected String[] getOutput(Rss20.Channel channelArg){
		ArrayList<String> rtnList = new ArrayList<String>();
		rtnList.add(StringUtils.getTimestampString());
		rtnList.add("[Channel]title:"+channelArg.getTitle());
		rtnList.add("[Channel]link:"+channelArg.link);
		rtnList.add("[Channel]description:"+channelArg.getDescription());
		int i=0;
        for (Item elm: channelArg.getItems()){
            rtnList.add(String.format("[item(%d)]title:%s"
            		, i, elm.title));
            rtnList.add(String.format("[item(%d)]link:%s"
            		, i, elm.link));
            rtnList.add(String.format("[item(%d)]pubDate:%s"
            		, i, elm.pubdate));
            rtnList.add(String.format("[item(%d)]description:"
            		, i, elm.description));
            i++;}
        return rtnList.toArray(new String[0]);
	}
	protected String[] getOutput(Rss10.Channel channelArg){
		ArrayList<String> rtnList = new ArrayList<String>();
		rtnList.add(StringUtils.getTimestampString());
		rtnList.add("[Channel]title:"+channelArg.title);
		rtnList.add("[Channel]link:"+channelArg.link);
		rtnList.add("[Channel]date:"+channelArg.dc_date);
		rtnList.add("[Channel]description:"+channelArg.description);
		return rtnList.toArray(new String[0]);
	}
	protected String[] getOutput(List<Rss10.Item> itemListArg){
		ArrayList<String> rtnList = new ArrayList<String>();
		int i=0;
		for ( Rss10.Item elm : itemListArg){
			rtnList.add(String.format("[item(%d)]title:%s"
					, i, elm.title));
			rtnList.add(String.format("[item(%d)]link:%s"
					, i, elm.link));
			rtnList.add(String.format("[item(%d)]date:%s"
					, i, elm.date));
			rtnList.add(String.format("[item(%d)]description:%s"
					, i, elm.description));
			rtnList.add(String.format("[item(%d)]subject:%s"
					, i, elm.subject));
			i++;}
		return rtnList.toArray(new String[0]);
	}
	public RssIF doOutput() {
		if ( rssIF instanceof Rss20){
			Rss20 rss20 = (Rss20)rssIF;
			Channel channel = (Channel)rss20.channel;
			for ( String line : getOutput(channel)){
				doOutput( line );}}
		else if( rssIF instanceof Rss10){
			Rss10 rss10 = (Rss10)rssIF;
			Rss10.Channel channel = (Rss10.Channel)rss10.channel;
			for ( String line : getOutput( channel )){
				doOutput( line );}
			List<Rss10.Item> items = rss10.items;
			for ( String line : getOutput( items )){
				doOutput( line );}}
		return rssIF;
	}
}//Unreachable