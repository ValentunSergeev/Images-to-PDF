package swati4star.createpdf.viewModel;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import junit.framework.TestCase;

import org.json.JSONException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import swati4star.createpdf.util.RecentUtil;

import static org.mockito.Mockito.when;
import static swati4star.createpdf.util.RecentUtil.RecentList;

@RunWith(MockitoJUnitRunner.class)
public class HomeViewModelTest extends TestCase {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();


    @Mock
    RecentUtil util;

    @Test
    public void shouldLoadRecentsAtInit() throws JSONException {
        RecentList list = new RecentList(new LinkedHashMap<>());

        when(util.getList()).thenReturn(list);

        HomeViewModel viewModel = new HomeViewModel(util);

        assertEquals(viewModel.recentListLiveData.getValue(), list);
    }

    @Test
    public void shouldAddToRecents() throws JSONException {
        RecentList initial = new RecentList(new LinkedHashMap<>());

        HashMap<String, String> feature = new HashMap<>();
        int id  = 1;

        RecentList updated = new RecentList(new LinkedHashMap<String, Map<String, String>>() {{
            put(String.valueOf(id), feature);
        }});

        when(util.getList()).thenReturn(initial);
        when(util.addFeatureInRecentList(id, feature)).thenReturn(updated);

        HomeViewModel viewModel = new HomeViewModel(util);

        viewModel.featureClicked(id, feature);

        assertEquals(viewModel.recentListLiveData.getValue(), updated);
    }
}