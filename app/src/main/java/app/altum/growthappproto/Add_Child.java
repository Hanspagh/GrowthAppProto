package app.altum.growthappproto;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class Add_Child extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__child);

        Button addChild = (Button) findViewById(R.id.addChildButton);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText name = (EditText) findViewById(R.id.name);
                EditText height = (EditText) findViewById(R.id.height);
                EditText weight = (EditText) findViewById(R.id.weight);
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                Child child = new Child(name.getText().toString(), height.getText().toString(), weight.getText().toString());
                realm.copyToRealm(child);
                realm.commitTransaction();
                Add_Child.this.finish();
            }
        });



    }
}
