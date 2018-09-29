package jp.newgreat.rss.parser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss10.Channel;
import jp.newgreat.rss.models.Rss10.Item;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.util.Constants.Status;
import jp.newgreat.rss.util.LogUtils;

public class StaxRss10Parser implements RssParserIF{
	private InputStream inputStream = null;
	public StaxRss10Parser(InputStream arg){
		this.inputStream = arg;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		AccepteeIF rtn  = v.visit( this );
		return rtn;
	}
    public RssIF parse(){
    		LogUtils.d("=== entering parse()");
        RssIF   rtn      = null;
        Status  stat     = null;
        List<Item> items = new ArrayList<Item>();
        Item    item     = null;
        Rss10   rss10    = null;
        Channel channel  = null;
        String  text     = "";

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
            		if ( "RDF".equals(localName)
    				  || "rdf:RDF".equals(localName)){
            			rss10 = new Rss10();
            		}
                if ("channel".equals(localName)){
                		stat = Status.CHANNEL;
                		if ( rss10 != null){
                			channel = rss10.new Channel();}}
                if ("item".equals(localName)){
                		stat = Status.ITEM;
                    item = new Rss10().new Item();}
                if ( "title".equals(localName)){
                		text="";}
                if ( "description".equals(localName)){
            			text="";}
                if ( "subject".equals(localName)){
            			text="";}
                if ( "link".equals(localName)){
            			text="";}
                if ( "date".equals(localName)){
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
                			channel.title = text;
                			text="";}
                		else{
                			item.title = text;
                			text="";}
                break;
                case "link":
                		if ( stat == Status.CHANNEL){
                			channel.link = text;
                			text="";}
                		else{
                			item.link = text;
                			text="";}
                    break;
                case "description":
            		if ( stat == Status.CHANNEL){
            			channel.description = text;
            			text="";}
            		else{
            			item.description = text;
            			text="";}
	        		break;
                case "subject":
            		if ( stat == Status.ITEM){
            			item.subject = text;
            			text="";}
	        			break;
                case "date":
	            		if ( stat == Status.CHANNEL){
	            			channel.dc_date = text;
	            			text="";}
	            		else{
	                		item.date = text;
	                		text="";}
            		break;
                }
                break;
                }
            }
        }
        if ( rss10 != null )
        		rss10.items = items;
		}//End of Try
		catch(XMLStreamException e)
		{
		LogUtils.e(e);
		}
		if ( rss10 != null ){
			rss10.channel = channel;
			rtn = rss10;}
		LogUtils.d( rss10 );
        return rtn;
    }
}