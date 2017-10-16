package widgets.dinesh.com.paytmproject;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

public interface APIService {
    @GET("sentimental_analysis/")
    Flowable<List<SentimentalAnalysisData>> getSentimentalAnalysisData(@Query("keyword") String keyword);

    @GET("twitter_entities/")
    Flowable<List<TwitterEntity>> getTwitterEntities();

    @POST("twitter_entities/")
    Observable<TwitterEntity> addTwitterEntity(@Body TwitterEntity twitterEntity);
}
