package cn.org.tcse.soapexpress.tif;

import java.io.File;
import java.io.FileFilter;
import java.util.HashMap;
import java.util.Map;

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
	
	public static Store getInstant(String path) {
		if(store == null) {
			store = new Store(path);
		}
		return store;
	}
	
	private Store(String _path) {
		if(_path.equals("") || _path==null)
			return;
		if(!isInited)
			init(_path);
	}

	private void init(String _path) {
		isInited = true;
		System.out.println("[INFO] init store");
		path = _path;
		initEAMap();
		initEvent();
		initAction();
	}

	private boolean initEAMap() {
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		if (!mapFolder.exists()) {
			System.err.println("[ERROR] the maps file path "
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
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		if (!eventFolder.exists()) {
			System.err.println("[ERROR] the event file path "
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
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		if (!actionFolder.exists()) {
			System.err.println("[ERROR] the event file path "
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
		eventMap.put(eventId, event);
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		File eventFile = new File(eventFolder, eventId + ".xml");
		event.toFile(eventFile);
	}
	
	public void removeEvent(String eventId) {
		eventMap.remove(eventId);
		File eventFolder = new File(path + Constant.FILE_SEPARATOR + Constant.EVENT_FOLDER);
		File eventFile = new File(eventFolder, eventId + ".xml");
		if(eventFile.exists())
			eventFile.delete();
	}
	
	public Map<String, Action> getActions() {
		return actionMap;
	}
	
	public Action getAction(String serviceFlowName) {
		return actionMap.get(serviceFlowName);
	}

	public void addAction(Action action) {
		String actionId = action.getServiceFlowName();
		actionMap.put(actionId, action);
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		File actionFile = new File(actionFolder, actionId + ".xml");
		action.toFile(actionFile);
	}
	
	public void removeAction(String serviceFlowName) {
		actionMap.remove(serviceFlowName);
		File actionFolder = new File(path + Constant.FILE_SEPARATOR + Constant.ACTION_FOLDER);
		File actionFile = new File(actionFolder, serviceFlowName + ".xml");
		if(actionFile.exists())
			actionFile.delete();
	}

	public Map<String, EAMap> getEamapMap() {
		return eamapMap;
	}
	
	public EAMap getEAMaps(String appName) {
		return eamapMap.get(appName);
	}

	public void addEAMap(EAMap eamap) {
		String mapId = eamap.getApplicationName();
		eamapMap.put(mapId, eamap);
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		File mapFile = new File(mapFolder, mapId + ".xml");
		eamap.toFile(mapFile);
	}
	
	public void removeEAMap(String appName) {
		eamapMap.remove(appName);
		File mapFolder = new File(path + Constant.FILE_SEPARATOR + Constant.MAP_FOLDER);
		File mapFile = new File(mapFolder, appName + ".xml");
		if(mapFile.exists())
			mapFile.delete();
	}
}
