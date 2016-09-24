package app.altum.growthappproto;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.formatter.AxisValueFormatter;

public class ChartExample extends AppCompatActivity {

    private int mFillColor = Color.GREEN;
    private LineChart mChart2;
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_example);
        mChart = (LineChart) findViewById(R.id.chart);
        mChart2 = (LineChart) findViewById(R.id.chart2);

        mChart.setBackgroundColor(Color.WHITE);
        mChart.setGridBackgroundColor(mFillColor);
        mChart.setDrawGridBackground(true);
        mChart.setDrawBorders(true);
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
