package hut.plugin.log;

import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties properties;
	private static String OwlDocument;
	private static String OwlSourceCode;

	/**
	 * Tên file cấu hình
	 */
	private static final String PROPERTY_FILE = "config.properties";


	public static String getOwlDocument() {
		return OwlDocument;
	}


	public static String getOwlSourceCode() {
		return OwlSourceCode;
	}


	public static Properties getProperties() {
		return properties;
	}


	/**
	 * Load file thuộc tính vào lớp Properties
	 */
    public static Properties loadProperty() {
        Properties properties = new Properties();
        try {
        	//System.out.println(ClassLoader.getSystemResource(PROPERTY_FILE));
            properties.load(ClassLoader.getSystemResourceAsStream(PROPERTY_FILE));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return properties;
    }

	/**
	 * Sinh lại và lưu các thông số cấu hình trong file cấu hình
	 */
	public static int regenerateProperties()
	{
		properties = loadProperty();//Load từ file cấu hình
		if (properties == null ) {
			System.out.println("null null");
		}else {
			System.out.println("XXXXXXXXXX");
		}
		OwlDocument=properties.getProperty("OwlDocument");
		OwlSourceCode=properties.getProperty("OwlSourceCode");
		System.out.println(OwlDocument);
		System.out.println(OwlSourceCode);
		return 0;
	}

}
