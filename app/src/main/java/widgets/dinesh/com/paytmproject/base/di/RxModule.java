package widgets.dinesh.com.paytmproject.base.di;


import dagger.Module;
import dagger.Provides;
import widgets.dinesh.com.paytmproject.base.data.BackgroundThread;
import widgets.dinesh.com.paytmproject.base.data.ExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.PostExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.UIThread;


@ScopedActivity
@Module
public class RxModule {

    @Provides
    @ScopedActivity
    public ExecutionThread provideExecutionThread() {
        return new BackgroundThread();
    }

    @ScopedActivity
    @Provides
    public PostExecutionThread providePostExecutionThread() {
        return new UIThread();
    }

}
