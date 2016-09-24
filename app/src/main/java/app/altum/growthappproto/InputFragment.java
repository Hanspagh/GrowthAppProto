package app.altum.growthappproto;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */


public class InputFragment extends DialogFragment {

    private EditText editWeight;
    private EditText editHeight;
    private EditText editDate;
    private Button saveButton;
    private Button cancelButton;
    private SimpleDateFormat simpleDateFormat;

    public InputFragment() {
        // Empty constructor required for DialogFragment
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.input_fragment, container);
        editWeight = (EditText) view.findViewById(R.id.edit_weight);
        editHeight = (EditText) view.findViewById(R.id.edit_height);
        editDate = (EditText) view.findViewById(R.id.edit_date);

        //set default today as date
        simpleDateFormat = new SimpleDateFormat( "yyyy/MM/dd" );
        editDate.setText( simpleDateFormat.format( new Date() ));

        //setup Buttons
        setupCancelButton(view);
        setupSaveButton(view);

        //TODO udfyld med barnets navn
        getDialog().setTitle("Indtast data");

        return view;
    }

    //Used for setting style
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TitledDialog);
        super.onCreate(savedInstanceState);
    }

    private void setupCancelButton(View view){
        cancelButton = (Button) view.findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

    private void setupSaveButton(View view){
        saveButton = (Button) view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (saveInputData()) {
                    //Data saved TODO, show whether saved
                    getDialog().dismiss();
                }
                getDialog().dismiss();
            }
        });
    }

    private boolean saveInputData(){
        String wText = editWeight.getText().toString();
        String hText = editHeight.getText().toString();
        try {
            Child currentChild = new Child();//stub TODO
            currentChild.setBirthday(new Date());
            Date date = simpleDateFormat.parse(editDate.getText().toString());
            float weight = 0;
            float height = 0;

            if (!wText.equals(""))
                weight = Float.parseFloat(wText);
            if (!hText.equals(""))
                height = Float.parseFloat(hText);

            int months = monthsBetweenIgnoreDays(currentChild.getBirthday(), date);

            if (weight != 0) {
               WeightEntry wEntry = new WeightEntry(weight, months, date);
                //TODO save this entry to child
            }
            if (height != 0) {
                HeightEntry hEntry = new HeightEntry(height, months, date);
                //TODO save this entry to child
            }


            return true;
        } catch (ParseException e){
            return false;
        }

        //Float weight = Float.parseFloat(wText);
        //Integer height = Integer.parseInt(hText);
    }

    private static int monthsBetweenIgnoreDays(Date start, Date end) {
        int startYear = start.getYear();
        int endYear = end.getYear();
        int startMonth = start.getMonth();
        int endMonth = end.getMonth();
        int monthValue;
        if (startYear != endYear)
            monthValue = startMonth + endMonth;
        else monthValue = endMonth - startMonth;

        return 12 * (endYear - startYear) + monthValue;
    }
}

