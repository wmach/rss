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
import jp.newgreat.rss.models.Html;
import jp.newgreat.rss.models.Html.Anchor;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.util.Constants.HtmlStatus;
import jp.newgreat.rss.util.LogUtils;

public class HtmlParser implements HtmlParserIF {
	private InputStream inputStream = null;
	public HtmlParser(InputStream arg){
		this.inputStream = arg;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		AccepteeIF rtn  = v.visit( this );
		return rtn;
	}
    public HtmlIF parse(){
    		LogUtils.d("=== entering parse()");
        HtmlIF   rtn     = null;
        @SuppressWarnings("unused")
		HtmlStatus stat  = null;
        List<Anchor> anchors = new ArrayList<Anchor>();
        Anchor  anchor   = null;
        Html    html     = new Html();
                html.body= html.new Body();
        String  text     = "";

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader reader = null;
		try
		{
	        reader = factory.createXMLStreamReader(inputStream);
	        if ( reader == null){
	        		System.err.println("reader == null!!!");
	        } else {
	        		System.err.println("reader is not NULL !!!");
	        }
	        while (reader.hasNext()) {
	            int Event = reader.next();
	            switch (Event) {
		            case XMLStreamConstants.START_ELEMENT: {
		            		String localName = reader.getLocalName();
		            		System.err.println("localName=="+localName);
		            		if ( "a".equals(localName)){
		            			anchor = html.new Anchor();
		            			text = "";
		            			stat = HtmlStatus.ANCHOR;
		            			int count = reader.getAttributeCount();
		            			for ( int i=0; i<count; i++){
		            				String name= reader.getAttributeName(i).toString();
		            				System.err.println("attribute.name=="+name);
		            				if ( "href".equals(name)){
		            					String link = reader.getAttributeValue(i);
		            					anchor.link = link;
		            					System.err.println("href=="+link);
		            					break;}}
		            		}
		                break;
		            }
//		            case XMLStreamConstants.ATTRIBUTE:{
//		            		String localName = reader.getLocalName();
//		            		System.err.println("ATTRIB.localName=="+localName);
//		            		if ( "a".equals(localName)){
//		            			int count = reader.getAttributeCount();
//		            			for ( int i=0; i<count; i++){
//		            				String name= reader.getAttributeName(i).toString();
//		            				System.err.println("attribute.name=="+name);
//		            				if ( "href".equals(name)){
//		            					anchor.link=reader.getAttributeValue(i);
//		            					break;}}}
//		            		break;
//		            }
		            case XMLStreamConstants.CHARACTERS: {
		                text += reader.getText().trim();
		                break;
		            }
		            case XMLStreamConstants.END_ELEMENT: {
		            		String localName = reader.getLocalName();
		            		System.err.println("END:LocalName"+localName);
		                switch ( localName ) {
			                case "a":
			                		System.err.println("a text=="+text);
			                		anchor.content=text;
			                    anchors.add(anchor);
			                    break;
			            }
		                break;
		            }
	            }
	        }
	        if ( anchors != null && anchors.size()>0){
	            System.err.println("anchors.size()=="+String.valueOf(anchors.size()));
	        		html.body.anchors = anchors;
	        		rtn=html;
	        	}
	        System.err.println("LoopBreak;");
		}//End of Try
		catch(XMLStreamException e)
		{
			LogUtils.e(e);
		}
		LogUtils.d( html );
        return rtn;
    }
}//Unreachable