package widgets.dinesh.com.paytmproject.analysis.data;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import widgets.dinesh.com.paytmproject.APIService;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;
import widgets.dinesh.com.paytmproject.base.data.BaseDataProvider;
import widgets.dinesh.com.paytmproject.base.data.ExecutionThread;
import widgets.dinesh.com.paytmproject.base.data.PostExecutionThread;


public class AnalysisDataProvider extends BaseDataProvider implements AnalysisDataSource{
    private final AnalysisDataDAO analysisDataDAO;
    private final APIService apiService;

    @Inject
    public AnalysisDataProvider(AnalysisDataDAO analysisDataDAO, APIService apiService,
                                ExecutionThread executionThread,
                                PostExecutionThread postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.analysisDataDAO = analysisDataDAO;
        this.apiService = apiService;
    }

    @Override
    public Flowable<List<SentimentalAnalysisData>> getData(String keyword) {
        apiService.getSentimentalAnalysisData(keyword)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler())
                .subscribe(sentimentalAnalysisData -> Observable.fromCallable(() -> {
                    analysisDataDAO.insertAll(sentimentalAnalysisData);
                    return sentimentalAnalysisData;
                }).subscribeOn(executionThread.getScheduler())
                        .observeOn(postExecutionThread.getScheduler())
                        .subscribe()
                ,error->{
                            Log.e("API Error", "Something went wrong");
                        });
        return analysisDataDAO.getAll(keyword);

    }
}
