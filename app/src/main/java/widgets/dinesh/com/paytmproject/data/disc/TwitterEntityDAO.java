package widgets.dinesh.com.paytmproject.data.disc;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;
import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

@Dao
public interface TwitterEntityDAO {
    @Query("SELECT * FROM twitterentity")
    Flowable<List<TwitterEntity>> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<TwitterEntity> twitterEntities);
}
