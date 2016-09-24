package app.altum.growthappproto;


import android.app.Activity;
import android.content.DialogInterface;
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

import io.realm.Realm;


/**
 * A simple {@link Fragment} subclass.
 */


public class InputFragment extends DialogFragment{

    public interface MyDialogCloseListener
    {
        public void handleDialogClose(DialogInterface dialog);//or whatever args you want
    }

    public static final String CHILD_ID = "child_id";
    private EditText editWeight;
    private EditText editHeight;
    private EditText editDate;
    private Button saveButton;
    private Button cancelButton;
    private SimpleDateFormat simpleDateFormat;
    private Child child;

    public InputFragment() {
        // Empty constructor required for DialogFragment
    }

    public static InputFragment newInstance(Child child) {

        Bundle args = new Bundle();
        args.putString(CHILD_ID, child.getName());
        InputFragment fragment = new InputFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDismiss(DialogInterface dialog)
    {
        Activity activity = getActivity();
        if(activity instanceof MyDialogCloseListener)
            ((MyDialogCloseListener)activity).handleDialogClose(dialog);
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
        String childId = getArguments().getString(CHILD_ID);
        child = Realm.getDefaultInstance().where(Child.class).equalTo("name", childId).findFirst();
        //TODO udfyld med barnets navn
        getDialog().setTitle("Indtast data for");

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
            Date date = simpleDateFormat.parse(editDate.getText().toString());
            float weight = 0;
            float height = 0;

            //Handle partial entries
            if (!wText.equals(""))
                weight = Float.parseFloat(wText);
            if (!hText.equals(""))
                height = Float.parseFloat(hText);

            int months = monthsBetweenIgnoreDays(child.getBirthday(), date);


            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();

            if (weight != 0) {
                WeightEntry wEntry = new WeightEntry(weight, months, date);
                child.getWeightData().add(wEntry);
            }
            if (height != 0) {
                HeightEntry hEntry = new HeightEntry(height, months, date);
                child.getHeightData().add(hEntry);
            }
            realm.commitTransaction();

            return true;
        } catch (ParseException e){
            return false;
        }
    }

    private static int monthsBetweenIgnoreDays(Date start, Date end) {
        int startYear = start.getYear();
        int endYear = end.getYear();
        int startMonth = start.getMonth();
        int endMonth = end.getMonth();
        int monthValue;

        //handle same year months
        if (startYear != endYear) monthValue = startMonth + endMonth;
        else monthValue = endMonth - startMonth;

        return 12 * (endYear - startYear) + monthValue;
    }
}

