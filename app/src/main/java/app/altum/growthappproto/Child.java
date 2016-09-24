package app.altum.growthappproto;

import io.realm.RealmList;
import io.realm.RealmObject;
import java.util.Date;

public class Child extends RealmObject {


    private String name;

    private String height, weight;
    private RealmList<HeightEntry> heightData;
    private RealmList<WeightEntry> weightData;
    private Date birthday;

    public Child() {
    }

    public Child(String name, String height, String weight, Date birthday) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public RealmList<WeightEntry> getWeightData() {
        return weightData;
    }

    public RealmList<HeightEntry> getHeightData() {
        return heightData;
    }

}