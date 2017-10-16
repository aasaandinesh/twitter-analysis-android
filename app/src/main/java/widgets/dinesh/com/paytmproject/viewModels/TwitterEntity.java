package widgets.dinesh.com.paytmproject.viewModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class TwitterEntity {

    @SerializedName("keyword")
    @Expose
    @PrimaryKey
    @NonNull
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(@NonNull String keyword) {
        this.keyword = keyword;
    }


}
