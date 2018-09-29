package jp.newgreat.rss;

import jp.newgreat.rss.util.Constants.Mode;
import jp.newgreat.rss.util.LogUtils;

//エントリーポイント
public class RssCollector {
	public static void main(String[] args){
		RssCollector self = new RssCollector();
		self.doOperation( args );
	}
	private void doOperation(String[] args){
		//エラー出力先を指定
		LogUtils.setMode(Mode.FILE);
		//ビジネスロジック作成
		LogicVisitor v = new LogicVisitor();
		//ループ作成
		AccepteeIF accept = new Looper( args );
		//エントリ
		accept.accept(v);
	}
}//Unreachable