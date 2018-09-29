package jp.newgreat.rss;

import jp.newgreat.rss.reader.UrlReader;
import jp.newgreat.rss.result.TerminalAcceptee;

public class Looper implements AccepteeIF {
	private String[] urls;
	public Looper(String[] args){
		urls = args;
	}
	@Override public AccepteeIF accept(LogicVisitor v) {
		AccepteeIF acceptee = null;
		for ( String elm : urls ){
			acceptee = new UrlReader( elm );
			while ( !(acceptee instanceof TerminalAcceptee)){
				acceptee = acceptee.accept( v );}}
		return acceptee;
	}
}
