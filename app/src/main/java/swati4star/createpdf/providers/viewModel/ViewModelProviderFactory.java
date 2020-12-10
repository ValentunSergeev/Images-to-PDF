package swati4star.createpdf.providers.viewModel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class ViewModelProviderFactory implements ViewModelProvider.Factory {
    private static ViewModelProviderFactory INSTANCE;

    private final Map<Class<?>, Provider<?>> registry = new HashMap<>();

    private ViewModelProviderFactory() { }

    public synchronized static ViewModelProviderFactory getInstance() {
        ensureCreated();

        return INSTANCE;
    }

    public synchronized static <T extends ViewModel> void addProvider(Class<T> key, Provider<T> provider) {
        ensureCreated();

        if (INSTANCE.registry.get(key) == null) {
            INSTANCE.registry.put(key, provider);
        }
    }

    private synchronized static void ensureCreated() {
        if (INSTANCE == null) {
            INSTANCE = new ViewModelProviderFactory();
        }
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        for (Map.Entry<Class<?>, Provider<?>> entry : registry.entrySet()) {
            if (modelClass.isAssignableFrom(entry.getKey())) {
                return (T) entry.getValue().get();
            }
        }

        throw new IllegalArgumentException("Unknown viewModelClass: " + modelClass.getSimpleName());
    }
}
