package widgets.dinesh.com.paytmproject.data.disc;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import widgets.dinesh.com.paytmproject.base.di.ApplicationContext;

/**
 * Created by ajmac1005 on 14/10/17.
 */
@Module
public class DiscDataModule {

    @Provides
    @Singleton
    public SharedPrefService provideSharedPrefService(@ApplicationContext Context context) {
        return new SharedPrefManager(context);
    }

    @Provides
    @Singleton
    public PaytmDatabase provideDatabase(@ApplicationContext Context context){
        return Room.databaseBuilder(context,
                PaytmDatabase.class, "paytm").build();
    }
}
