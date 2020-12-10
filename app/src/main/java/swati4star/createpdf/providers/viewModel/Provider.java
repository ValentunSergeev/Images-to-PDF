package swati4star.createpdf.providers.viewModel;

import android.arch.lifecycle.ViewModel;

public interface Provider<T extends ViewModel> {
    T get();
}
