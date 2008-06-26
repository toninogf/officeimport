package cn.org.tcse.soapexpress.tif;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.www.alf.eventManager.admin.ALFAdminProxy;

public class AdminClient {

	private static String applicationName = "tifdemo";

	private static ALFAdminProxy proxy = null;

	private static Logger logger = Logger.getLogger(AdminClient.class);

	static {
		class LoadProperties {
			public Properties load() {
				Properties props = new Properties();
				String rep = Store.getInstant().getPath();
				File confFile = new File(rep + Constant.FILE_SEPARATOR
						+ Constant.TFI_PROPERTIES);
				logger.debug("conf file: " + confFile);
				InputStream cin = null;
				try {
					cin = new FileInputStream(confFile);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					logger.error("maybe the config file is not existed: " + confFile.getAbsolutePath());
				}
				try {
					props.load(cin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return props;
			}
		}
		Properties props = new LoadProperties().load();
		String url = props.getProperty("tifadminurl",
				"http://localhost:8080/ALFEventManager/services/ALFAdmin");
		logger.info("use tif server endpoint:" + url);
		proxy = new ALFAdminProxy(url);
	}

	public static String deploy(String eventMapXML) {
		try {
			logger.info("deploy map");
			return proxy.deploy(eventMapXML);
		} catch (Exception e) {
			logger.error("deploy fail:\n" + eventMapXML);
			return "fail";
		}
	}

	public static void unDeploy(String appName) {
		if (appName == null) {
			appName = applicationName;
			logger.warn("appName is null. use the default:" + appName);
		}
		try {
			logger.info("undeploy map:" + appName);
			proxy.unDeploy(appName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resume(String appName) {
		if (appName == null) {
			appName = applicationName;
			logger.warn("appName is null. use the default:" + appName);
		}
		try {
			logger.info("resume map:" + appName);
			proxy.resume(appName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getStatus(String appName) {
		if (appName == null) {
			appName = applicationName;
			logger.warn("appName is null. use the default:" + appName);
		}
		try {
			int statusCode = proxy.status(appName).getStatus().getValue();
			if (statusCode == 1) {
				logger.info("get " + appName + " status: Running");
				return "Running";
			} else {
				logger.info("get " + appName + " status: Paused");
				return "Paused";
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("get " + appName + " status: Unknown");
			return "Unknown";
		}
	}

	public static void pause(String appName) {
		if (appName == null) {
			appName = applicationName;
			logger.warn("appName is null. use the default:" + appName);
		}
		try {
			proxy.pause(appName);
			logger.info("pause map:" + appName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		FileReader fin = new FileReader("tifdemo.xml");
		BufferedReader br = new BufferedReader(fin);
		StringBuffer sb = new StringBuffer();
		String line = br.readLine();
		while (line != null) {
			sb.append(line);
			sb.append("\n");
			line = br.readLine();
		}
		deploy(sb.toString());
		resume(null);
		System.out.println(getStatus(null));
		pause(null);
		System.out.println(getStatus(null));
		unDeploy(null);
	}

}
