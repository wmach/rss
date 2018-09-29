package jp.newgreat.rss.reader;

import java.io.InputStream;

import jp.newgreat.rss.AccepteeIF;

public interface ReaderIF extends AccepteeIF{
	public InputStream getInputStream();
}
