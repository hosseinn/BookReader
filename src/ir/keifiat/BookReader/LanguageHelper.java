package ir.keifiat.BookReader;

import android.content.Context;
import android.content.res.Configuration;

import java.util.Locale;

/**
 * Created by hossein on 8/21/15.
 */
public class LanguageHelper {

    LanguageHelper(Context cx) {

        // Farsi

        Locale locale = new Locale("fa");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        cx.getResources().updateConfiguration(config,
                cx.getResources().getDisplayMetrics());
    }
}
