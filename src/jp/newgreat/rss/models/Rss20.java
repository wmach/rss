package jp.newgreat.rss.models;

import java.util.List;

public class Rss20 implements RssIF{
	public Channel channel;
	public class Channel{
		public String     title;
		public String     link;
		public String     description;
		public String     lastbuilddate;
		public String     docs;
		public String     generator;
		public List<Item> items;
		public String getTitle(){return title;}
		public void setTitle(String title){this.title = title;}
		public String getLink(){return link;}
		public void setLink(String link){this.link = link;}
		public String getDescription(){return description;}
		public void setDescription(String description){this.description = description;}
		public String getLastbuilddate(){return lastbuilddate;}
		public void setLastbuilddate(String lastbuilddate){this.lastbuilddate = lastbuilddate;}
		public String getDocs(){return docs;}
		public void setDocs(String docs){this.docs = docs;}
		public String getGenerator(){return generator;}
		public void setGenerator(String generator){this.generator = generator;}
		public List<Item> getItems(){return items;}
		public void setItems(List<Item> items){this.items = items;}
	}
	public class Item{
		public String title;
		public String link;
		public String description;
		public String pubdate;
		public String guid;
		public String getTitle(){return title;}
		public void setTitle(String title){this.title = title;}
		public String getLink(){return link;}
		public void setLink(String link){this.link = link;}
		public String getDescription(){return description;}
		public void setDescription(String description){this.description = description;}
		public String getPubdate(){return pubdate;}
		public void setPubdate(String pubdate){this.pubdate = pubdate;}
		public String getGuid(){return guid;}
		public void setGuid(String guid){this.guid=guid;}
	}
}
