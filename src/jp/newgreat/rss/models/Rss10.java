package jp.newgreat.rss.models;

import java.util.List;

public class Rss10 implements RssIF {
	public Channel    channel;
	public List<Item> items;
	public class Channel{
		public String title;
		public String link;
		public String description;
		public String dc_date;
		public String dc_language;
		public List<RdfSeq> items;
	}
	public class RdfSeq{
		public String rdf_li;
	}
	public class Item{
		public String title;
		public String link;
		public String description;
		public String subject;
		public String creator;
		public String date;
	}
}//Unreachable