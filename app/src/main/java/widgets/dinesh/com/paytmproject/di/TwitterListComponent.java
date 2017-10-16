package widgets.dinesh.com.paytmproject.di;

import dagger.Component;
import widgets.dinesh.com.paytmproject.TwitterListActivity;
import widgets.dinesh.com.paytmproject.base.di.RxModule;
import widgets.dinesh.com.paytmproject.base.di.ScopedActivity;

@ScopedActivity
@Component(
        modules = {
                TwitterListModule.class,
                RxModule.class
        },
        dependencies = ApplicationComponent.class

)
public interface TwitterListComponent {
    void inject(TwitterListActivity activity);
}
