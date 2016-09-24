package app.altum.growthappproto;

import java.util.Date;

import io.realm.RealmObject;


public class WeightEntry extends RealmObject {

    private float weight;
    private int ageInMonths;
    private Date recordedDate;

    public WeightEntry(){}

    public WeightEntry(float weight, int ageInMonths, Date recordedDate){
        this.weight = weight;
        this.ageInMonths = ageInMonths;
        this.recordedDate = recordedDate;
    }

    public float getWeight() {
        return weight;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }


}
