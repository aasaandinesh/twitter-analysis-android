package widgets.dinesh.com.paytmproject.analysis.data;

import java.util.List;

import io.reactivex.Flowable;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;

public interface AnalysisDataSource {
    Flowable<List<SentimentalAnalysisData>> getData(String keyword);
}
