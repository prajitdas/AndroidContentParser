package com.prajitdas.parserapp.util;

import android.content.pm.ProviderInfo;

import com.prajitdas.parserapp.ParserApplication;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class ProviderContent {
	public static void addItem(String providerCount, ProviderInfo providerAuthority) {
		addItem(new ProviderItem(providerCount, providerAuthority));
	}
	
	public static void addItem(ProviderItem item) {
		ParserApplication.ITEMS.add(item);
		ParserApplication.ITEM_MAP.put(item.id, item);
	}

	/**
	 * A dummy item representing a piece of content.
	 */
	public static class ProviderItem {
		public String id;
		public ProviderInfo content;

		public ProviderItem(String id, ProviderInfo content) {
			this.id = id;
			this.content = content;
		}

		@Override
		public String toString() {
			return content.name.substring(content.name.lastIndexOf('.') + 1);
		}
	}
}