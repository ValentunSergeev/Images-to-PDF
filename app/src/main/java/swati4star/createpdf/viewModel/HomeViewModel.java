package swati4star.createpdf.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.preference.PreferenceManager;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.Map;

import swati4star.createpdf.util.RecentUtil;

import static swati4star.createpdf.util.RecentUtil.*;

public class HomeViewModel extends ViewModel {
    private final RecentUtil recentUtil;

    private final MutableLiveData<RecentList> _recentListLiveData = new MutableLiveData<>();
    public final LiveData<RecentList> recentListLiveData = _recentListLiveData;

    public HomeViewModel(RecentUtil recentUtil) {
        this.recentUtil = recentUtil;

        loadRecents();
    }

    private void loadRecents() {
        try {
            RecentList recentList = recentUtil.getList();

            _recentListLiveData.setValue(recentList);
        } catch (JSONException e) {
            // error handling maybe here
            e.printStackTrace();
        }
    }

    public void featureClicked(int id, Map<String, String> feature) {
        try {
            RecentList updated = recentUtil.addFeatureInRecentList(id, feature);

            _recentListLiveData.setValue(updated);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
