package app.altum.growthappproto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class ChildOverview extends AppCompatActivity {

    private List<Child> childList = new ArrayList();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter child_grid_Adapter;
    private RecyclerView.LayoutManager child_grid_Layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(ChildOverview.this, AddChild.class);
                ChildOverview.this.startActivity(myIntent);
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.child_grid);

        child_grid_Adapter = new MyAdapter(childList);
        child_grid_Layout = new GridLayoutManager(this, 2);

        recyclerView.setAdapter(child_grid_Adapter);
        recyclerView.setLayoutManager(child_grid_Layout);

        Intent intent = getIntent();

/*        if(intent.getStringExtra("name") != null){
            String name = intent.getStringExtra("name");
            String height = intent.getStringExtra("height");
            String weight = intent.getStringExtra("weight");
            String birthday = intent.getStringExtra("birthday");
            Boolean gender = intent.getBooleanExtra("gender");

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Date birthdayDate = new Date();
            try {
                sdf.parse(birthday);
            } catch (ParseException e){
                e.printStackTrace();
            }

            Child childToAdd = new Child(name, height, weight, birthdayDate);
            childList.add(childToAdd);
            child_grid_Adapter.notifyDataSetChanged();
        }*/
//        prepareChildData();
        loadChildrenFromDB();


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadChildrenFromDB();
    }

    private void loadChildrenFromDB() {
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<Child> where = realm.where(Child.class);
        RealmResults<Child> all = where.findAll();
        childList.clear();

        childList.addAll(all);
        child_grid_Adapter.notifyDataSetChanged();

    }

    private void prepareChildData(){
        Date testDate = new Date();
//        Child child = new Child("Steffen", "200", "110", testDate, true);
//        childList.add(child);
//
//        child = new Child("Hans", "173", "80", testDate, true);
//        childList.add(child);
//
//        child = new Child("Martin", "150", "92", testDate, false);
//        childList.add(child);
//
//        child = new Child("Emil", "194", "87", testDate, false);
//        childList.add(child);

        child_grid_Adapter.notifyDataSetChanged();
    }

}
