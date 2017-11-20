package widgets.dinesh.com.paytmproject.addEntity.di;

import dagger.Component;
import widgets.dinesh.com.paytmproject.addEntity.ui.activities.AddTwitterEntityActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.base.di.ScopedActivity;
import widgets.dinesh.com.paytmproject.di.ApplicationComponent;

@ScopedActivity
@Component(
        modules = {
                AddEntityModule.class,
                RxModule.class
        },
        dependencies = ApplicationComponent.class

)
public interface AddEntityComponent {
    void inject(AddTwitterEntityActivity twitterEntityActivity);
}
