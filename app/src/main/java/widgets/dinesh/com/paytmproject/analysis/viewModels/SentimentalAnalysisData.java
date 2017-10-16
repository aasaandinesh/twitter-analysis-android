package widgets.dinesh.com.paytmproject.analysis.viewModels;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import widgets.dinesh.com.paytmproject.viewModels.TwitterEntity;

@Entity(foreignKeys = @ForeignKey(entity = TwitterEntity.class,
        parentColumns = "keyword",
        childColumns = "keyword"),
        indices = {@Index(value = {"keyword", "city"}, unique = true)})
public class SentimentalAnalysisData {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("keyword")
    @Expose
    private String keyword;

    @SerializedName("count")
    @Expose
    private int count;

    @SerializedName("score")
    @Expose
    private float score;

    @SerializedName("latitude")
    @Expose
    private float latitude;

    @SerializedName("longitude")
    @Expose
    private float longitude;

    @SerializedName("city")
    @Expose
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
