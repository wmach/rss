package jp.newgreat.rss.util;

public class Constants {
	public static enum Mode{
		  STDOUT(0)
		, STDERR(1)
		, FILE(2)
		;
        private int val;
        Mode(int i){this.val=i;}
        public int val(){return this.val;}
	}
	public static enum HtmlStatus{
		  HTML(0)
		, BODY(1)
		, ANCHOR(2)
		;
        private int val;
        HtmlStatus(int i){this.val=i;}
        public int val(){return this.val;}
	}
    public static enum Status{
          CHANNEL(0)
        , ITEM   (1)
        ;
        private int val;
        Status(int i){this.val=i;}
        public int val(){return this.val;}
    }
    public static enum Fields{
    		  CHANNEL_TITLE      (0)
    		, CHANNEL_DESCRIPTION(1)
    		, ITEM_TITLE         (2)
    		, ITEM_DESCRIPTION   (3)
    		;
    		private int val;
    		Fields(int i){this.val=i;}
    		public int val(){return this.val;}
    }
    public static final class Format{
    		public static final String yyyyMMddTHHmmssSSSZ = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    }
}