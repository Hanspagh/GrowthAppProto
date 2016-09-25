package app.altum.growthappproto;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.Realm;

public class AddChild extends AppCompatActivity {

    EditText name;
    EditText birthday;
    Spinner gender;

    final Date selectedDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        name = (EditText) findViewById(R.id.name);
        birthday = (EditText) findViewById(R.id.birthday);
        gender = (Spinner) findViewById(R.id.gender);

        Date currDate = new Date(); //update to current date OnCreate
        selectedDate.setDate(currDate.getDate());
        selectedDate.setMonth(currDate.getMonth());
        selectedDate.setYear(currDate.getYear());

        setDatePicker();

        final Spinner gender = (Spinner) findViewById(R.id.gender);
        String[] genders = new String[]{"Dreng", "Pige"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_text, genders);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(adapter);

        Button addChild = (Button) findViewById(R.id.addChildButton);
        addChild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isMan = (gender.getSelectedItem().toString().equals("Dreng"));
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
                Date bdayDate = new Date();
                try {
                    bdayDate = simpleDateFormat.parse(birthday.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Child child = new Child(name.getText().toString(),bdayDate, isMan);
                realm.copyToRealm(child);
                realm.commitTransaction();
                AddChild.this.finish();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setDatePicker() {
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                selectedDate.setYear(year - 1900);
                selectedDate.setMonth(monthOfYear);
                selectedDate.setDate(dayOfMonth);
                updateLabel();
            }
        };

        birthday.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddChild.this, date,
                        selectedDate.getYear() + 1900, selectedDate.getMonth(),
                        selectedDate.getDate()).show();

            }
        });

    }

    private void updateLabel() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        birthday.setText(simpleDateFormat.format(selectedDate));
    }
}
