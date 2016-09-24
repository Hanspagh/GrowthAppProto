package app.altum.growthappproto;

import android.content.Intent;
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

        child_grid_Adapter = new MyAdapter(this,childList);
        child_grid_Layout = new GridLayoutManager(this, 2);

        recyclerView.setAdapter(child_grid_Adapter);
        recyclerView.setLayoutManager(child_grid_Layout);

        Intent intent = getIntent();

        if(intent.getStringExtra("name") != null){
            String name = intent.getStringExtra("name");
            String height = intent.getStringExtra("height");
            String weight = intent.getStringExtra("weight");
            String birthday = intent.getStringExtra("birthday");

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
        }
        //prepareChildData();



    }

    private void prepareChildData(){
        Date testDate = new Date();
        Child child = new Child("Steffen", "200", "110", testDate);
        childList.add(child);

        child = new Child("Hans", "173", "80", testDate);
        childList.add(child);

        child = new Child("Martin", "150", "92", testDate);
        childList.add(child);

        child = new Child("Emil", "194", "87", testDate);
        childList.add(child);

        child_grid_Adapter.notifyDataSetChanged();
    }

}
