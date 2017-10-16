package widgets.dinesh.com.paytmproject.analysis.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;


@Dao
public interface AnalysisDataDAO {
    @Query("SELECT * FROM sentimentalanalysisdata  as a join twitterentity as t on  t.keyword = a.keyword where t.keyword=:keyword")
    Flowable<List<SentimentalAnalysisData>> getAll(String keyword);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<SentimentalAnalysisData> sentimentalAnalysisData);
}
