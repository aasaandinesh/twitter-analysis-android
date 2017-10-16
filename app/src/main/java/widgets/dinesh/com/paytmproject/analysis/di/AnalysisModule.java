package widgets.dinesh.com.paytmproject.analysis.di;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import widgets.dinesh.com.paytmproject.APIService;
import widgets.dinesh.com.paytmproject.analysis.data.AnalysisDataDAO;
import widgets.dinesh.com.paytmproject.analysis.data.AnalysisDataProvider;
import widgets.dinesh.com.paytmproject.analysis.data.AnalysisDataSource;
import widgets.dinesh.com.paytmproject.analysis.ui.MainActivity;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisViewModel;
import widgets.dinesh.com.paytmproject.base.data.ExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.PostExecutionThread;
import widgets.dinesh.com.paytmproject.data.disc.PaytmDatabase;

@Module
public class AnalysisModule {
    private final MainActivity activity;

    public AnalysisModule(MainActivity activity) {
        this.activity = activity;
    }
    @Provides
    public AnalysisDataDAO getDAO(PaytmDatabase paytmDatabase){
        return paytmDatabase.analysisDataDAO();
    }

    @Provides
    public APIService getService(Retrofit retrofit){
        return retrofit.create(APIService.class);
    }


    @Provides
    public AnalysisDataSource getDataSource(AnalysisDataDAO dao, APIService apiService, ExecutionThread executionThread, PostExecutionThread postExecutionThread){
        return new AnalysisDataProvider(dao, apiService, executionThread, postExecutionThread);

    }


    @Provides
    public SentimentalAnalysisViewModel getTwitterEntitiesViewModel(AnalysisDataSource dataSource){
        return new SentimentalAnalysisViewModel(dataSource);
    }

}
