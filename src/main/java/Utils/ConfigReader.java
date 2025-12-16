package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	public static Properties prop;

	public static Properties loadConfig() {
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream("src/test/resources/Config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

}
