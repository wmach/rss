package jp.newgreat.rss.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.Rss20;
import jp.newgreat.rss.models.Rss20.Channel;
import jp.newgreat.rss.models.Rss20.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.Constants.Status;
import jp.newgreat.rss.util.LogUtils;

public class StaxRss20Parser implements RssParserIF{
	private InputStream inputStream = null;
	public StaxRss20Parser(InputStream arg){
		this.inputStream = arg;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		AccepteeIF rtn  = v.visit( this );
		return rtn;
	}
    public RssIF parse(){
        RssIF rtn        = null;
        Status stat      = null;
        List<Item> items = new ArrayList<Item>();
        Item item        = null;
        Rss20 rss20      = null;
        Channel channel  = null;
        String text      = "";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
		try
		{
        reader = factory.createXMLStreamReader(inputStream);
        while (reader.hasNext()) {
            int Event = reader.next();
            switch (Event) {
            case XMLStreamConstants.START_ELEMENT: {
            		String localName = reader.getLocalName();
            		if ( "rss".equals(localName)){
            			rss20 = new Rss20();
            		}
                if ("channel".equals(localName)){
                		stat = Status.CHANNEL;
                		if ( rss20 != null){
                			channel = rss20.new Channel();}}
                if ("item".equals(localName)){
                		stat = Status.ITEM;
                    item = new Rss20().new Item();}
                if ( "title".equals(localName)){
                		text="";}
                if ( "link".equals(localName)){
            			text="";}
                if ( "description".equals(localName)){
                		text="";}
                break;
            }
            case XMLStreamConstants.CHARACTERS: {
                text += reader.getText().trim();
                break;
            }
            case XMLStreamConstants.END_ELEMENT: {
                switch (reader.getLocalName()) {
                case "item":
                    items.add(item);
                    break;
                case "title":
                		if ( stat == Status.CHANNEL){
                			channel.setTitle(text);
                			text="";}
                		else{
                			item.setTitle(text);
                			text="";}
                    break;
                case "lastbuilddate":
                		channel.setLastbuilddate(text);
                		text="";
                		break;
                case "docs":
                		channel.setDocs(text);
                		text="";
                		break;
                case "generator":
                		channel.setGenerator(text);
                		text="";
                		break;
                case "link":
                		if ( stat == Status.CHANNEL){
                			channel.setLink(text);
                			text="";}
                		else{
                			item.setLink(text);
                			text="";}
                    break;
                case "description":
                		if ( stat == Status.CHANNEL){
                			channel.setDescription(text);
                			text="";}
                		else{
                			item.setDescription(text);
                			text="";}
            			break;
                case "pubdate":
                		item.setPubdate(text);
                		text="";
                		break;
                case "guid":
                		item.setGuid(text);
                		text="";
                		break;
                }
                break;
                }
            }
        }
        channel.setItems(items);
		}//End of Try
		catch(Exception e)
		{
		LogUtils.e(e);
		return rtn;
		}
		if ( rss20 != null ){
			rss20.channel = channel;
			rtn = rss20;}
		LogUtils.d( rss20 );
        return rtn;
    }
}