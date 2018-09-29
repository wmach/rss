package jp.newgreat.rss;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.newgreat.rss.editor.EllipsizeEditor;
import jp.newgreat.rss.editor.LinkEditor;
import jp.newgreat.rss.editor.TagRemoveEditor;
import jp.newgreat.rss.models.Html;
import jp.newgreat.rss.models.HtmlIF;
import jp.newgreat.rss.models.Rss10;
import jp.newgreat.rss.models.Rss20;
import jp.newgreat.rss.models.RssIF;
import jp.newgreat.rss.parser.HtmlParser;
import jp.newgreat.rss.parser.StaxRss10Parser;
import jp.newgreat.rss.parser.StaxRss20Parser;
import jp.newgreat.rss.reader.FileReader;
import jp.newgreat.rss.reader.UrlReader;
import jp.newgreat.rss.result.FileResult;
import jp.newgreat.rss.result.PrintResult;
import jp.newgreat.rss.result.TerminalAcceptee;
import jp.newgreat.rss.util.Constants.Fields;
import jp.newgreat.rss.util.LogUtils;

//ビジネスロジック
public class LogicVisitor {
	//_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
	//_/
	//_/Private Methods
	//_/
	private String mStringFromInputStream = null;
	private void setInputStream(InputStream isArg){
		BufferedReader br = new BufferedReader(new InputStreamReader(isArg));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = br.readLine()) != null) {
			    sb.append(line);}
			br.close();}
		catch (IOException e) {
			LogUtils.e(e);}
		this.mStringFromInputStream = sb.toString();
		System.err.println("=== mStringFromInputStream");
		System.err.println(mStringFromInputStream);
		System.err.println("--- mStringFromInputStream");
	}
	private InputStream getInputStream(){
		InputStream rtn = null;
		if ( mStringFromInputStream!=null){
			byte[] ba = null;
			try {
				ba = mStringFromInputStream.getBytes("utf-8");}
			catch (UnsupportedEncodingException e){
				e.printStackTrace();}
			if ( ba != null){
				rtn = new ByteArrayInputStream( ba );}}
		return rtn;
	}
	//_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/_/
	//_/
	//_/Public Methods
	//_/
	//RSSリーダー（URL入力 or ファイル名入力）
	public AccepteeIF visit(UrlReader arg){
		HtmlParser rtn = null;
		InputStream is = arg.getInputStream();
		if ( is==null ){
			is = new FileReader(arg.getUrlString()).getInputStream();}
		if ( is!=null ){
			setInputStream( is );}
		rtn = new HtmlParser( getInputStream());
		return rtn;
	}
	//Htmlパーサー
	public AccepteeIF visit(HtmlParser arg){
		LinkEditor rtn = null;
		HtmlIF html = arg.parse();
		if ( html == null){
			return new StaxRss20Parser( getInputStream());}
		else {
			rtn = new LinkEditor( html);}
		return rtn;
	}
	//RSSパーサー（RSS1.0のStaxParser）
	public AccepteeIF visit(StaxRss10Parser arg) {
		TagRemoveEditor rtn = null;
		RssIF rss = arg.parse();
		LogUtils.d( (Rss10)rss );
		rtn = new TagRemoveEditor( rss );
		return rtn;
	}
	//RSSパーサー（RSS2.0のStaxParser）
	public AccepteeIF visit(StaxRss20Parser arg){
		TagRemoveEditor rtn = null;
		RssIF rss = arg.parse();
		if ( rss == null){
			return new StaxRss10Parser( getInputStream());}
		else {
			rtn = new TagRemoveEditor( rss );}
		return rtn;
	}
	//RSS編集（HTMLタグの削除と空白などの削除）
	public AccepteeIF visit(TagRemoveEditor arg){
		EllipsizeEditor rtn = null;
		List<Fields> list = new ArrayList<Fields>();
		list.add(Fields.ITEM_TITLE);
		list.add(Fields.ITEM_DESCRIPTION);
		RssIF rss = arg.edit( list );
		rtn = new EllipsizeEditor( rss );
		if ( rss instanceof Rss20)
			LogUtils.d( (Rss20)rss );
		else
			LogUtils.d( (Rss10)rss );
		return rtn;
	}
	//RSS編集（後略）
	public AccepteeIF visit(EllipsizeEditor arg){
		PrintResult rtn = null;
		Map<Fields, String> map = new HashMap<Fields, String>();
		map.put(Fields.ITEM_TITLE, "10");
		map.put(Fields.ITEM_DESCRIPTION, "30");
		RssIF rss = (RssIF)arg.edit(map);
		rtn = new PrintResult(rss);
		if ( rss instanceof Rss20)
			LogUtils.d( (Rss20) rss );
		else
			LogUtils.d( (Rss10) rss );
		return rtn;
	}
	public AccepteeIF visit(LinkEditor arg){
		PrintResult rtn = null;
		HtmlIF html = arg.edit();
		rtn = new PrintResult( html );
		LogUtils.d( (HtmlIF) html);
		return rtn;
	}
	//RSS入力の結果出力（標準出力stdout）
	public AccepteeIF visit(PrintResult arg){
		FileResult rtn = null;
		RssIF rssIF = arg.doOutput();
		rtn = new FileResult( rssIF );
		if (rssIF instanceof Html)
			LogUtils.d( (Html) rssIF);
		else if ( rssIF instanceof Rss10)
			LogUtils.d( (Rss10) rssIF );
		else if ( rssIF instanceof Rss20)
			LogUtils.d( (Rss20) rssIF);
		return rtn;
	}
	//RSS入力の結果出力（ファイル）
	public AccepteeIF visit(FileResult arg){
		TerminalAcceptee rtn = null;
		@SuppressWarnings("unused")
		RssIF rssIF = arg.doOutput();
		rtn = new TerminalAcceptee( arg );
		return rtn;
	}
}//Unreachable