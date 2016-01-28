package edu.umbc.cs.ebiquity.mithril.parserapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.widget.Toast;
import edu.umbc.cs.ebiquity.mithril.parserapp.util.ProviderContent.ProviderItem;
/**
 * @author prajit.das
 */
public class ParserApplication extends Application {
	private static final String [] PermissionsParserApp = {
			Manifest.permission.READ_CONTACTS,
			Manifest.permission.READ_CALL_LOG,
			Manifest.permission.WRITE_EXTERNAL_STORAGE,
			Manifest.permission.READ_EXTERNAL_STORAGE,
			"com.google.android.providers.gsf.permission.READ_GSERVICES"
		};

	public static final String XPRIVACY_CONST_COL_UID = "Uid";
	
	public static final String XPRIVACY_CONST_COL_RESTRICTION = "Restriction";
	
	public static final String XPRIVACY_CONST_COL_RESTRICTED = "Restricted";
	
	public static final String XPRIVACY_CONST_COL_METHOD = "Method";
	
	public static final String XPRIVACY_CONST_COL_USED = "Used";
	
	public static final String XPRIVACY_CONST_COL_SETTING = "Setting";
	
	public static final String XPRIVACY_CONST_COL_VALUE = "Value";
	
	private static final int CONST_PERMISSION_READ_CONTACTS = 1;

	private static final int CONST_PERMISSION_READ_CALL_LOG = 1;
	
	private static final int CONST_PERMISSION_WRITE_EXTERNAL_STORAGE = 1;
	
	private static final int CONST_PERMISSION_READ_EXTERNAL_STORAGE = 1;
	
	private static final int CONST_PERMISSION_READ_GSERVICES = 1;

	private static final String CONST_EBANDMW_CONTENT_URI = "content://edu.umbc.cs.ebiquity.mithril.command.contentprovider.Content";

	private static final String CONST_XPRIVACY_CONTENT_URI = "content://biz.bokhorst.xprivacy.provider/usage";

	private static final String CONST_AUDIOS = "audios";

	private static final String CONST_CONTACTS = "contacts";

	private static final String CONST_FAKE = "fake";

	private static final String CONST_FILES = "files";

	private static final String CONST_IMAGES = "images";

	private static final String CONTACT_BUTTON_LOADER = "getContactUsingloader";

	private static final String CONTACT_BUTTON_QUERY = "getContactUsingQuery";
	
	private static final String CONST_SLASH = "/";

	public static String getConstAudios() {
		return CONST_AUDIOS;
	}

	public static String getConstContacts() {
		return CONST_CONTACTS;
	}

	public static String getConstFake() {
		return CONST_FAKE;
	}
	public static String getConstFiles() {
		return CONST_FILES;
	}

	public static String getConstImages() {
		return CONST_IMAGES;
	}
	
	public static String getConstEbAndMWContentUri() {
		return CONST_EBANDMW_CONTENT_URI;
	}

	public static String getContactButtonLoader() {
		return CONTACT_BUTTON_LOADER;
	}

	public static String getContactButtonQuery() {
		return CONTACT_BUTTON_QUERY;
	}

	public static String getContactProvider() {
		return CONTACT_PROVIDER;
	}

	public static String getContactsProvider() {
		return CONTACTS_PROVIDER;
	}

	public static String getDebugTag() {
		return DEBUG_TAG;
	}
	
	public static Map<String, ProviderItem> getITEM_MAP() {
		return ITEM_MAP;
	}

	public static List<ProviderItem> getITEMS() {
		return ITEMS;
	}

	public static String [] getITEMSStringVal() {
		String [] tempList = new String[ITEMS.size()];
		for(int i = 0; i < ITEMS.size(); i++)
			tempList[i] = ITEMS.get(i).toString();
		return tempList;
	}

	public static String getMediaButtonLoader() {
		return MEDIA_BUTTON_LOADER;
	}

	public static String getMediaButtonQuery() {
		return MEDIA_BUTTON_QUERY;
	}

	public static int getProviderCount() {
		return ProviderCount;
	}

	public static String getQueryOrLoader() {
		return queryOrLoader;
	}

	/**
	 * @return the singleton
	 */
	public static ParserApplication getSingleton() {
		return singleton;
	}
	
	public static void makeToast(Context context, String someString) {
		Toast.makeText(context, someString, Toast.LENGTH_LONG).show();
	}

	public static void setITEM_MAP(Map<String, ProviderItem> iTEM_MAP) {
		ITEM_MAP = iTEM_MAP;
	}
	public static void setITEMS(List<ProviderItem> iTEMS) {
		ITEMS = iTEMS;
	}
	
	public static void setProviderCount(int providerCount) {
		ProviderCount = providerCount;
	} 
	
	public static void setQueryOrLoader(String aQueryOrLoaderString) {
		queryOrLoader = aQueryOrLoaderString;
	}
	
	/**
	 * @param singleton the singleton to set
	 */
	public static void setSingleton(ParserApplication singleton) {
		ParserApplication.singleton = singleton;
	}

	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACT_PROVIDER = "ContactProvider";

	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACTS_PROVIDER = "ContactsProvider";

	private static final String DEBUG_TAG = "ParserApplicationDebugTag";

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, ProviderItem> ITEM_MAP = new HashMap<String, ProviderItem>();
	
	/**
	 * An array of sample (dummy) items.
	 */
	public static List<ProviderItem> ITEMS = new ArrayList<ProviderItem>();

	private static final String MEDIA_BUTTON_LOADER = "getMediaUsingloader";

	private static final String MEDIA_BUTTON_QUERY = "getMediaUsingQuery";

	private static int ProviderCount;
	
	private static String queryOrLoader;

	private static ParserApplication singleton;

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		setSingleton(this);
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}

	public static String getConstSlash() {
		return CONST_SLASH;
	}

	public static String [] getPermissionsParserApp() {
		return PermissionsParserApp;
	}

	public static int getConstPermissionReadContacts() {
		return CONST_PERMISSION_READ_CONTACTS;
	}

	public static int getConstPermissionReadCallLog() {
		return CONST_PERMISSION_READ_CALL_LOG;
	}

	public static int getConstPermissionWriteExternalStorage() {
		return CONST_PERMISSION_WRITE_EXTERNAL_STORAGE;
	}

	public static int getConstPermissionReadExternalStorage() {
		return CONST_PERMISSION_READ_EXTERNAL_STORAGE;
	}

	public static int getConstPermissionReadGservices() {
		return CONST_PERMISSION_READ_GSERVICES;
	}

	public static String getConstXprivacyContentUri() {
		return CONST_XPRIVACY_CONTENT_URI;
	}
}