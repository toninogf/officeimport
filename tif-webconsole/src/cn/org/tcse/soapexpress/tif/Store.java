package cn.org.tcse.soapexpress.tif;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.org.tcse.soapexpress.tif.model.Action;
import cn.org.tcse.soapexpress.tif.model.EAMap;
import cn.org.tcse.soapexpress.tif.model.Event;

public class Store {
	private static String path = null;
	private static Map<String, Event> eventMap = new HashMap<String, Event>();
	private static Map<String, Action> actionMap = new HashMap<String, Action>();
	private static Map<String, EAMap> eamapMap = new HashMap<String, EAMap>();
	private static boolean isInited = false;
	private static Store store = null;
	
	private static Logger logger = Logger.getLogger(Store.class);
	
	public static Store getInstance() {
		if(!isInited) {
			logger.error("must init store with the repository path, please use getInstant(String path)");
			return null;
		}
		return store;
	}
	
	public static Store getInstance(String path) {
		if(path.equals("") || path==null) {
			logger.error("repository path can't be null");
			return null;
		}
		if(store == null) {
			store = new Store(path);
		}
		return store;
	}
	
	private Store(String _path) {
		if(!isInited)
			init(_path);
	}

	private void init(String _path) {
		logger.info("init Store");
		isInited = true;
		path = _path;
		logger.info("use the repository: " + path);
		initEAMap();
		initEvent();
		initAction();
	}

	private boolean initEAMap() {
		logger.info("init eaMap List from files");
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		if (!mapFolder.exists()) {
			logger.warn("the maps file path "
					+ mapFolder.getAbsolutePath() + " dosen't exist! Will create it.");
			mapFolder.mkdirs();
			return true;
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File f) {
				if (f.isFile()
						|| f.getName().substring(f.getName().lastIndexOf("."))
								.equals("xml")) {
					return true;
				}
				return false;
			}
		};
		File[] mapFiles = mapFolder.listFiles(fileFilter);
		for (File mapFile : mapFiles) {
			EAMap eamap = EAMap.fromFile(mapFile);
			eamapMap.put(eamap.getApplicationName(), eamap);
		}
		return true;
	}

	private boolean initEvent() {
		logger.info("init Event List from files");
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		if (!eventFolder.exists()) {
			logger.warn("the event file path "
					+ eventFolder.getAbsolutePath() + " dosen't exist! Will create it.");
			eventFolder.mkdirs();
			return true;
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File f) {
				if (f.isFile()
						|| f.getName().substring(f.getName().lastIndexOf("."))
								.equals("xml")) {
					return true;
				}
				return false;
			}
		};
		File[] eventFiles = eventFolder.listFiles(fileFilter);
		for (File eventFile : eventFiles) {
			Event event = Event.fromFile(eventFile);
			eventMap.put(event.getEventType() + "-" + event.getProduct(), event);
		}
		return true;
	}
	
	private boolean initAction() {
		logger.info("init Action List from files");
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		if (!actionFolder.exists()) {
			logger.warn("the action file path "
					+ actionFolder.getAbsolutePath() + " dosen't exist! Will create it.");
			actionFolder.mkdirs();
			return true;
		}
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File f) {
				if (f.isFile()
						|| f.getName().substring(f.getName().lastIndexOf("."))
								.equals("xml")) {
					return true;
				}
				return false;
			}
		};
		File[] actionFiles = actionFolder.listFiles(fileFilter);
		for (File actionFile : actionFiles) {
			Action action = Action.fromFile(actionFile);
			actionMap.put(action.getServiceFlowName(), action);
		}
		return true;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		Store.path = path;
	}

	public Map<String, Event> getEvents() {
		return eventMap;
	}
	
	public Event getEvent(String eventId) {
		return eventMap.get(eventId);
	}

	public void addEvent(Event event) {
		String eventId = event.getEventType() + "-" + event.getProduct();
		logger.info("add event to store: " + eventId);
		eventMap.put(eventId, event);
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		File eventFile = new File(eventFolder, eventId + ".xml");
		logger.info("add event to file: " + eventFile.getAbsolutePath());
		event.toFile(eventFile);
	}
	
	public void removeEvent(String eventId) {
		logger.info("remove event from store: " + eventId);
		eventMap.remove(eventId);
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		File eventFile = new File(eventFolder, eventId + ".xml");
		if(eventFile.exists()) {
			logger.info("remove event from file: " + eventFile.getAbsolutePath());
			eventFile.delete();
		}
	}
	
	public Map<String, Action> getActions() {
		return actionMap;
	}
	
	public Action getAction(String serviceFlowName) {
		return actionMap.get(serviceFlowName);
	}

	public void addAction(Action action) {
		String actionId = action.getServiceFlowName();
		logger.info("add action to store: " + actionId);
		actionMap.put(actionId, action);
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		File actionFile = new File(actionFolder, actionId + ".xml");
		logger.info("add action to file: " + actionFile.getAbsolutePath());
		action.toFile(actionFile);
	}
	
	public void removeAction(String serviceFlowName) {
		logger.info("remove action from store: " + serviceFlowName);
		actionMap.remove(serviceFlowName);
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		File actionFile = new File(actionFolder, serviceFlowName + ".xml");
		if(actionFile.exists()) {
			actionFile.delete();
			logger.info("remove action from file: " + actionFile.getAbsolutePath());
		}
	}

	public Map<String, EAMap> getEamapMap() {
		return eamapMap;
	}
	
	public EAMap getEAMaps(String appName) {
		return eamapMap.get(appName);
	}

	public void addEAMap(EAMap eamap) {
		String mapId = eamap.getApplicationName();
		logger.info("add map to store: " + mapId);
		eamapMap.put(mapId, eamap);
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		File mapFile = new File(mapFolder, mapId + ".xml");
		logger.info("add map to file: " + mapFile.getAbsolutePath());
		eamap.toFile(mapFile);
	}
	
	public void removeEAMap(String appName) {
		logger.info("remove map from store: " + appName);
		eamapMap.remove(appName);
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		File mapFile = new File(mapFolder, appName + ".xml");
		if(mapFile.exists()) {
			mapFile.delete();
			logger.info("remove map from file: " + mapFile.getAbsolutePath());
		}
	}
}
