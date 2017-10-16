package widgets.dinesh.com.paytmproject.data.disc;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import widgets.dinesh.com.paytmproject.analysis.data.AnalysisDataDAO;
import widgets.dinesh.com.paytmproject.analysis.viewModels.SentimentalAnalysisData;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

@Database(entities = {TwitterEntity.class, SentimentalAnalysisData.class}, version = 1)
public abstract class PaytmDatabase extends RoomDatabase {
    public abstract TwitterEntityDAO twitterEntityDAO();
    public abstract AnalysisDataDAO analysisDataDAO();
}
