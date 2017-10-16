package widgets.dinesh.com.paytmproject.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import widgets.dinesh.com.paytmproject.base.di.ApplicationContext;
import widgets.dinesh.com.paytmproject.data.disc.DiscDataModule;
import widgets.dinesh.com.paytmproject.data.disc.PaytmDatabase;
import widgets.dinesh.com.paytmproject.data.disc.SharedPrefService;
import widgets.dinesh.com.paytmproject.data.network.NetworkModule;


@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                NetworkModule.class,
                DiscDataModule.class
        }
)
public interface ApplicationComponent {

    @ApplicationContext
    Context getApplicationContext();

    Application getApplication();
   Retrofit getRetrofit();

    SharedPrefService getSharedPrefService();

    PaytmDatabase provideDatabase();
}
