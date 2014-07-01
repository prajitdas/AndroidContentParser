package com.prajitdas.parserapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.prajitdas.parserapp.util.ProviderContent.ProviderItem;

import android.app.Application;
import android.content.res.Configuration;

public class ParserApplication extends Application {
	
	private static int ProviderCount;
	private static final String DEBUG_TAG = "ParserApplicationDebugTag";
	
	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACT_PROVIDER = "ContactProvider";

	/**
	 * public static final Strings for known content providers
	 */
	private static final String CONTACTS_PROVIDER = "ContactsProvider";

	/**
	 * An array of sample (dummy) items.
	 */
	public static List<ProviderItem> ITEMS = new ArrayList<ProviderItem>();

	/**
	 * A map of sample (dummy) items, by ID.
	 */
	public static Map<String, ProviderItem> ITEM_MAP = new HashMap<String, ProviderItem>();

	private static ParserApplication singleton;
	
	/**
	 * @return the singleton
	 */
	public static ParserApplication getSingleton() {
		return singleton;
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

	public static String getContactProvider() {
		return CONTACT_PROVIDER;
	}

	public static int getProviderCount() {
		return ProviderCount;
	}

	public static void setProviderCount(int providerCount) {
		ProviderCount = providerCount;
	}

	public static String getDebugTag() {
		return DEBUG_TAG;
	}
	
	public static String [] getITEMSStringVal() {
		String [] tempList = new String[ITEMS.size()];
		for(int i = 0; i < ITEMS.size(); i++)
			tempList[i] = ITEMS.get(i).toString();
		return tempList;
	}

	public static String getContactsProvider() {
		return CONTACTS_PROVIDER;
	}
}