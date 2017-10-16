package widgets.dinesh.com.paytmproject.analysis.viewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.LiveDataReactiveStreams;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import widgets.dinesh.com.paytmproject.analysis.data.AnalysisDataSource;

public class SentimentalAnalysisViewModel extends ViewModel {

    private final AnalysisDataSource dataSource;

    private LiveData<List<SentimentalAnalysisData>> analysisLiveData;

    @Inject
    public SentimentalAnalysisViewModel(AnalysisDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public LiveData<List<SentimentalAnalysisData>> getAnalysisLiveData(String keyword) {
        if(analysisLiveData==null){
            analysisLiveData = LiveDataReactiveStreams.fromPublisher(dataSource.getData(keyword));
        }
        return analysisLiveData;
    }
}
