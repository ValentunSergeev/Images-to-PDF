package swati4star.createpdf.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.preference.PreferenceManager;

import org.json.JSONException;

import java.util.LinkedHashMap;
import java.util.Map;

import swati4star.createpdf.util.RecentUtil;

public class HomeViewModel extends ViewModel {
    private final RecentUtil recentUtil;

    private final MutableLiveData<LinkedHashMap<String, Map<String, String>>> _recentListLiveData = new MutableLiveData<>();
    public final LiveData<LinkedHashMap<String, Map<String, String>>> recentListLiveData = _recentListLiveData;

    public HomeViewModel(RecentUtil recentUtil) {
        this.recentUtil = recentUtil;

        loadRecents();
    }

    private void loadRecents() {
        try {
            LinkedHashMap<String, Map<String, String>> recentList = recentUtil.getList();

            _recentListLiveData.setValue(recentList);
        } catch (JSONException e) {
            // error handling maybe here
            e.printStackTrace();
        }
    }

    public void featureClicked(int id, Map<String, String> feature) {
        try {
            recentUtil.addFeatureInRecentList(id, feature);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
