package swati4star.createpdf.providers.viewModel;

import android.content.SharedPreferences;

import swati4star.createpdf.App;
import swati4star.createpdf.preferences.SharedPreferenceFactory;
import swati4star.createpdf.util.RecentUtil;
import swati4star.createpdf.viewModel.HomeViewModel;

public class HomeProvider implements Provider<HomeViewModel> {

    @Override
    public HomeViewModel get() {
        SharedPreferences preferences = SharedPreferenceFactory.getInstance(App.INSTANCE);
        RecentUtil recentUtil = RecentUtil.getInstance(preferences);

        return new HomeViewModel(recentUtil);
    }
}
