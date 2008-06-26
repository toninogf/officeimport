package cn.org.tcse.soapexpress.tif;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.eclipse.www.alf.eventManager.admin.ALFAdminProxy;

public class AdminClient {

	private static String applicationName = "tifdemo";

	private static ALFAdminProxy proxy = null;

	static {
		class LoadProperties {
			public Properties load() {
				Properties props = new Properties();
				InputStream cin = this.getClass().getResourceAsStream(
						"tifclient.properties");
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
		proxy = new ALFAdminProxy(url);
	}

	public static String deploy(String eventMapXML) {
		try {
			return proxy.deploy(eventMapXML);
		} catch (Exception e) {
			return "Deploy Fail!";
		}
	}

	public static void unDeploy(String appName) {
		if (appName == null) {
			appName = applicationName;
		}
		try {
			proxy.unDeploy(appName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void resume(String appName) {
		if (appName == null) {
			appName = applicationName;
		}
		try {
			proxy.resume(appName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getStatus(String appName) {
		if (appName == null) {
			appName = applicationName;
		}
		try {
			int statusCode = proxy.status(appName).getStatus().getValue();
			if (statusCode == 1) {
				return "Running";
			} else {
				return "Paused";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Unknown";
		}
	}

	public static void pause(String appName) {
		if (appName == null) {
			appName = applicationName;
		}
		try {
			proxy.pause(appName);
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
