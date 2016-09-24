package app.altum.growthappproto;

import java.util.Date;


public class HeightEntry {

    private float height;
    private int ageInMonths;
    private Date recordedDate;

    public HeightEntry(float height, int ageInMonths, Date recordedDate){
        this.height = height;
        this.ageInMonths = ageInMonths;
        this.recordedDate = recordedDate;
    }

    public float getHeight() {
        return height;
    }

    public int getAgeInMonths() {
        return ageInMonths;
    }

    public Date getRecordedDate() {
        return recordedDate;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setAgeInMonths(int ageInMonths) {
        this.ageInMonths = ageInMonths;
    }

    public void setRecordedDate(Date recordedDate) {
        this.recordedDate = recordedDate;
    }
}
