package hut.plugin.log;

import org.apache.log4j.Logger;


/**
 * @author DatTT
 * Class minh họa cách sử dụng Log4J
 */
public class DemoLog4J {

	/**
	 * @param args
	 */
	private Logger logger=Logger.getLogger(this.getClass());
	public void testLog4J()
	{
		logger.info("Begin doing something ...");
		logger.info("Doing ...");
		logger.error("Get error!");
		logger.info("Done.");
	}
	public static void main(String[] args) {
		DemoLog4J demo= new DemoLog4J();
		demo.testLog4J();
	}

}
