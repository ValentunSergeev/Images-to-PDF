package swati4star.createpdf.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.preference.PreferenceManager;

public class SharedPreferenceFactory {
    private static SharedPreferences INSTANCE;

    private SharedPreferenceFactory() {}

    public synchronized static SharedPreferences getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = PreferenceManager.getDefaultSharedPreferences(context);
        }

        return INSTANCE;
    }
}
