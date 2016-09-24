package app.altum.growthappproto;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import io.realm.Realm;

public class AddChild extends AppCompatActivity {

    EditText name;
    EditText height;
    EditText weight;
    EditText birthday;
    final Date selectedDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        name = (EditText) findViewById(R.id.name);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        birthday = (EditText) findViewById(R.id.birthday);

        setDatePicker();

        Button addChild = (Button) findViewById(R.id.addChildButton);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date bdayDate = new Date();
                try {
                    bdayDate = simpleDateFormat.parse(birthday.getText().toString());
                } catch (ParseException e) { e.printStackTrace(); }
                Child child = new Child(name.getText().toString(), height.getText().toString(),
                              weight.getText().toString(), bdayDate);
                realm.copyToRealm(child);
                realm.commitTransaction();
                AddChild.this.finish();
            }
        });
    }

    private void setDatePicker(){
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                selectedDate.setYear(year-1900);
                selectedDate.setMonth(monthOfYear);
                selectedDate.setDate(dayOfMonth);
                updateLabel();
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddChild.this, date,
                        selectedDate.getYear()-1900, selectedDate.getMonth(),
                        selectedDate.getDate()).show();
            }
        });

    }

    private void updateLabel() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Log.d("ASDF", "DATE:" + simpleDateFormat.format(selectedDate));
        birthday.setText(simpleDateFormat.format(selectedDate));
    }
}
