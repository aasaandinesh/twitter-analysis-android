package widgets.dinesh.com.paytmproject.di;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import widgets.dinesh.com.paytmproject.APIService;
import widgets.dinesh.com.paytmproject.TwitterListActivity;
import widgets.dinesh.com.paytmproject.base.data.ExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.PostExecutionThread;
import widgets.dinesh.com.paytmproject.data.TwitterEntityDataProvider;
import widgets.dinesh.com.paytmproject.data.TwitterEntityDataSource;
import widgets.dinesh.com.paytmproject.data.disc.PaytmDatabase;
import widgets.dinesh.com.paytmproject.data.disc.TwitterEntityDAO;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntitiesViewModel;

@Module
public class TwitterListModule {
    private final TwitterListActivity activity;

    public TwitterListModule(TwitterListActivity activity) {
        this.activity = activity;
    }

    @Provides
    public TwitterEntityDAO getDAO(PaytmDatabase paytmDatabase){
        return paytmDatabase.twitterEntityDAO();
    }

    @Provides
    public APIService getService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }


    @Provides
    public TwitterEntityDataSource getDataSource(TwitterEntityDAO dao, APIService apiService, ExecutionThread executionThread, PostExecutionThread postExecutionThread){
        return new TwitterEntityDataProvider(dao, apiService, executionThread, postExecutionThread);

    }


    @Provides
    public TwitterEntitiesViewModel getTwitterEntitiesViewModel(TwitterEntityDataSource dataSource){
        return new TwitterEntitiesViewModel(dataSource);
    }

}
