package swati4star.createpdf;

import android.support.multidex.MultiDexApplication;

public class App extends MultiDexApplication {
    public static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
    }
}
