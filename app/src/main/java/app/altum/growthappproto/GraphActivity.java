package app.altum.growthappproto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class GraphActivity extends AppCompatActivity implements InputFragment.MyDialogCloseListener {
    public static final String CHILD_ID = "childId";
    private FloatingActionButton addButton;
    private LineChart mChart2;
    private int mFillColor = Color.argb(115, 113, 128, 219);
    private LineChart mChart;
    private Child child;
    private LineDataSet set2;
    private LineDataSet set1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        String childId = getIntent().getStringExtra(CHILD_ID);
        child = Realm.getDefaultInstance().where(Child.class).equalTo("name", childId).findFirst();
        toolbar.setTitle(child.getName());


        mChart = (LineChart) findViewById(R.id.chart);
        mChart2 = (LineChart) findViewById(R.id.chart2);
        addButton = (FloatingActionButton) findViewById(R.id.add_data_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment inputFragment = InputFragment.newInstance(child);

                inputFragment.show(getSupportFragmentManager(), "dialog");

                addUserData();
            }
        });
        addGraphs();
        addUserData();

    }

    @Override
    protected void onResume() {
        super.onResume();
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

    public void addUserData() {



        List<Entry> data = new ArrayList<>();

        for (WeightEntry heightEntry : child.getWeightData()) {
            data.add(new Entry(heightEntry.getAgeInMonths(), heightEntry.getWeight()));
        }

        if (set1 == null) {
            set1 = new LineDataSet(data, "User data");
            set1.setLineWidth(0);
            set1.enableDashedLine(1000,1000,1000);
            set1.setColor(Color.WHITE);
            set1.setCircleColor(Color.BLACK);
            set1.setCircleColorHole(Color.RED);
            set1.setColor(Color.WHITE);
            mChart2.getLineData().addDataSet(set1);
        } else {
            set1.clear();
            set1.setValues(data);
            mChart2.getData().notifyDataChanged();
            mChart2.notifyDataSetChanged();
        }


        List<Entry> data2 = new ArrayList<>();

        for (HeightEntry heightEntry : child.getHeightData()) {
            data2.add(new Entry(heightEntry.getAgeInMonths(), heightEntry.getHeight()));
        }

        if (set2 == null) {
            set2 = new LineDataSet(data2, "User data");
            set2.setLineWidth(0);
            set2.enableDashedLine(1000,1000,1000);
            set2.setColor(Color.WHITE);
            set2.setCircleColor(Color.BLACK);
            set2.setCircleColorHole(Color.RED);
            set2.setColor(Color.WHITE);
            mChart.getLineData().addDataSet(set2);
        } else {
            set2.clear();
            set2.setValues(data2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        }


    }

    public void addGraphs() {
        mChart.setDescription("Height");

        mChart.setBackgroundColor(Color.WHITE);
        mChart.setGridBackgroundColor(mFillColor);
        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setPinchZoom(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setDrawLabels(true);
        xAxis.setCenterAxisLabels(true);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.setAutoScaleMinMaxEnabled(true);
        Legend l = mChart.getLegend();
        l.setEnabled(false);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(false);

        mChart.getAxisRight().setEnabled(false);

        mChart2.setDescription("Weight");
        mChart2.setBackgroundColor(Color.WHITE);
        mChart2.setGridBackgroundColor(mFillColor);
        mChart2.setDrawGridBackground(true);
        mChart2.setDrawBorders(true);
        mChart2.setPinchZoom(true);
        XAxis xAxis2 = mChart2.getXAxis();
        xAxis2.setDrawGridLines(false);
        xAxis2.setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart2.setAutoScaleMinMaxEnabled(true);
        Legend l2 = mChart2.getLegend();
        l2.setEnabled(false);
        YAxis leftAxis2 = mChart2.getAxisLeft();
        leftAxis2.setDrawAxisLine(false);
        leftAxis2.setDrawZeroLine(false);
        leftAxis2.setDrawGridLines(false);
        mChart2.getAxisRight().setEnabled(false);
        setData();
    }

    private void setData() {

        LineData maleHeightData = new ChartDataProvider().getMaleHeightData(this, mChart);
        mChart.setData(maleHeightData);

        LineData maleHeightData2 = new ChartDataProvider().getMaleWeightData(this, mChart2);
        mChart2.setData(maleHeightData2);
    }


    @Override
    public void handleDialogClose(DialogInterface dialog) {
        addUserData();
    }
}
