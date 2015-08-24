package ir.keifiat.BookReader;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Date;

/**
 * Created by hossein on 8/8/15.
 */
public class StorageHelper {

    private static String StorageData = "Data-Area";

    public static void saveData(Context context) {
        SharedPreferences storage = context.getSharedPreferences(StorageData, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = storage.edit();

        int i = 0;
        e.putString("Name", "Hossein");
        e.putLong("lastUse", new Date().getTime());

        for(i = 0; i < 100; ++i) {
            // one key for each qos measurement
            String key="qos" + i;
            String value = String.format("%s|%s",123,456);
            e.putString(key,value);
            i++;
        }

        e.putInt("QoE", 4);

        e.commit();
    }

    public static void readData(Context context) {
        SharedPreferences storage = context.getSharedPreferences(StorageData, Context.MODE_PRIVATE);
        SharedPreferences.Editor e = storage.edit();

        int i = 0;
        e.putString("Name", "Hossein");
        e.putLong("lastUse", new Date().getTime());

        for(i = 0; i < 100; ++i) {
            // one key for each qos measurement
            String key="qos" + i;
            String value = String.format("%s|%s",123,456);
            e.putString(key,value);
            i++;
        }

        e.putInt("QoE", 4);

        e.commit();
    }
}
