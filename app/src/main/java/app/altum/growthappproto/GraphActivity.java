package app.altum.growthappproto;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;

public class GraphActivity extends AppCompatActivity {

    private FloatingActionButton addButton;
    private LineChart mChart2;
    private int mFillColor = Color.argb(255, 113, 128, 219);
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mChart = (LineChart) findViewById(R.id.chart);
        mChart2 = (LineChart) findViewById(R.id.chart2);
        addButton = (FloatingActionButton) findViewById(R.id.add_data_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment inputFragment = new InputFragment();
                inputFragment.show(getSupportFragmentManager(), "dialog");
            }
        });
        addGraphs();

    }

    public void addGraphs() {
        mChart.setBackgroundColor(Color.WHITE);
        mChart.setGridBackgroundColor(mFillColor);
        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
        mChart.setPinchZoom(true);
        XAxis xAxis = mChart.getXAxis();
        xAxis.setDrawGridLines(false);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mChart.setAutoScaleMinMaxEnabled(true);
        Legend l = mChart.getLegend();
        l.setEnabled(false);
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawGridLines(false);
        mChart.getAxisRight().setEnabled(false);

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


}
