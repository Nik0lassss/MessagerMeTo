package com.example.nikolas.messagernik.entity.system;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by User on 28.10.2015.
 */
public class ImageCache {
    final static int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    // Use 1/8th of the available memory for this memory cache.
    final static int cacheSize = maxMemory / 8;
    private static LruCache bitmaCache = new LruCache<String, Bitmap>(cacheSize) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // The cache size will be measured in kilobytes rather than
            // number of items.
            return bitmap.getByteCount() / 1024;
        }
    };
    public static void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            bitmaCache.put(key, bitmap);
        }
    }

    public static Bitmap getBitmapFromMemCache(String key) {
        Bitmap bitmap = null;
        Object object =bitmaCache.get(key);
        bitmap=(Bitmap) object;
        return bitmap;
    }
}
