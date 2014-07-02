package com.prajitdas.parserapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prajitdas.parserapp.util.ProviderContent.ProviderItem;

import android.app.Application;
import android.content.res.Configuration;

public class ParserApplication extends Application {
	
	private static final String BUTTON_LOADER = "loader";
	private static final String BUTTON_QUERY = "query";
	private static final String CALL_METHOD_TAG = "CALL_METHOD_TAG";

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

	private static int ProviderCount;

	private static String QueryOrLoader;

	private static ParserApplication singleton;

	public static String getButtonLoader() {
		return BUTTON_LOADER;
	}

	public static String getButtonQuery() {
		return BUTTON_QUERY;
	}

	public static String getCallMethodTag() {
		return CALL_METHOD_TAG;
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

	public static int getProviderCount() {
		return ProviderCount;
	}

	public static String getQueryOrLoader() {
		return QueryOrLoader;
	}

	/**
	 * @return the singleton
	 */
	public static ParserApplication getSingleton() {
		return singleton;
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

	public static void setQueryOrLoader(String queryOrLoader) {
		QueryOrLoader = queryOrLoader;
	}
	
	/**
	 * @param singleton the singleton to set
	 */
	public static void setSingleton(ParserApplication singleton) {
		ParserApplication.singleton = singleton;
	}

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
}