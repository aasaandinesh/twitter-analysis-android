package widgets.dinesh.com.paytmproject;

import android.support.multidex.MultiDexApplication;

import com.twitter.sdk.android.core.Twitter;

import widgets.dinesh.com.paytmproject.data.disc.DiscDataModule;
import widgets.dinesh.com.paytmproject.data.network.NetworkModule;
import widgets.dinesh.com.paytmproject.di.ApplicationComponent;
import widgets.dinesh.com.paytmproject.di.ApplicationModule;
import widgets.dinesh.com.paytmproject.di.DaggerApplicationComponent;



public class PaytmApplication extends MultiDexApplication {

    private static ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        Twitter.initialize(this);

        component =  DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .discDataModule(new DiscDataModule())
                .networkModule(new NetworkModule())
                .build();


    }

    public static ApplicationComponent getComponent() {
        return component;
    }
}
