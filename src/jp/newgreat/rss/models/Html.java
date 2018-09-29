package jp.newgreat.rss.models;

import java.util.List;

public class Html implements HtmlIF {
	public Html html;
	public Body body;
	public class Body{
		public List<Anchor> anchors;
	}
	public class Anchor extends Tag{
		public String link;
	}
	public class Tag{
		public String style;
		public String content;
	}
}//Unreachable