package app.altum.growthappproto;

import io.realm.RealmList;
import io.realm.RealmObject;
import java.util.Date;

public class Child extends RealmObject {


    private String name;

    private RealmList<HeightEntry> heightData;
    private RealmList<WeightEntry> weightData;
    private Date birthday;
    private Boolean isMan;

    public Child() {
    }

    public Child(String name, Date birthday, Boolean isMan) {
        this.name = name;
        this.birthday = birthday;
        this.isMan = isMan;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public RealmList<WeightEntry> getWeightData() {
        return weightData;
    }

    public RealmList<HeightEntry> getHeightData() {
        return heightData;
    }
}

