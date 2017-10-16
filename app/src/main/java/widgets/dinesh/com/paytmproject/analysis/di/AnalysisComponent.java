package widgets.dinesh.com.paytmproject.analysis.di;

import dagger.Component;
import widgets.dinesh.com.paytmproject.analysis.ui.MainActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.base.di.ScopedActivity;
import widgets.dinesh.com.paytmproject.di.ApplicationComponent;

@ScopedActivity
@Component(
        modules = {
                AnalysisModule.class,
                RxModule.class
        },
        dependencies = ApplicationComponent.class

)
public interface AnalysisComponent {
    void inject(MainActivity activity);
}
