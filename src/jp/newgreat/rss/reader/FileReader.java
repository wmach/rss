package jp.newgreat.rss.reader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import jp.newgreat.rss.AccepteeIF;
import jp.newgreat.rss.LogicVisitor;
import jp.newgreat.rss.util.LogUtils;

public class FileReader implements ReaderIF{
	private String fileName = null;
	public FileReader(String fileName){
		this.fileName = fileName;
	}
	@Override public InputStream getInputStream(){
		InputStream rtn = null;
		File f = new File( fileName );
		try {
			rtn = new FileInputStream( f );
		} catch (FileNotFoundException e) {
			LogUtils.e(e);
		}
		return rtn;
	}
	@Override
	public AccepteeIF accept(LogicVisitor v) {
		return null;
	}
}