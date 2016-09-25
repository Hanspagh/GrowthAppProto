package app.altum.growthappproto;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.FillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import app.altum.growthappproto.trendslines.PolyTrendLine;
import app.altum.growthappproto.trendslines.TrendLine;

public class ChartDataProvider {

    public Pair<LineData, Pair<TrendLine, TrendLine>> getMaleHeightData(Context context, final LineChart mChart) {
        List<List<Entry>> maleHeightData = new CsvReader().getMaleHeightData(context);

        List<Entry> yVals1 = maleHeightData.get(0);
        List<Entry> yVals2 = maleHeightData.get(1);
        List<Entry> yVals3 = maleHeightData.get(2);
        TrendLine trendLineLow = getTrendLine(yVals1);
        TrendLine trendLineHigh = getTrendLine(yVals2);
        return new Pair<>(getLineData(mChart, yVals1, yVals2, yVals3), new Pair<>(trendLineLow, trendLineHigh));
    }

    public Pair<LineData, Pair<TrendLine, TrendLine>> getMaleWeightData(Context context, final LineChart mChart) {
        List<List<Entry>> maleHeightData = new CsvReader().getMaleWeightData(context);

        List<Entry> yVals2 = maleHeightData.get(1);
        List<Entry> yVals3 = maleHeightData.get(2);
        List<Entry> yVals1 = maleHeightData.get(0);
        TrendLine trendLineLow = getTrendLine(yVals1);
        TrendLine trendLineHigh = getTrendLine(yVals2);

        return new Pair<>(getLineData(mChart, yVals1, yVals2, yVals3), new Pair<>(trendLineLow, trendLineHigh));
    }



    public TrendLine getTrendLine(List<Entry> data) {
        double[] x = new double[data.size()];
        double[] y = new double[data.size()];
        for(int i = 0; i < data.size() ; i++) {
            y[i] = data.get(i).getXIndex();
            x[i] = data.get(i).getVal();
        }

        TrendLine t = new PolyTrendLine(20);
        t.setValues(x,y);
        return t;
    }

    @NonNull
    private LineData getLineData(final LineChart mChart, List<Entry> yVals1, List<Entry> yVals2, List<Entry> yVals3) {
        LineDataSet set1, set2, set3;
        // create a dataset and give it a type
        set1 = new LineDataSet(yVals1, "DataSet 1");

        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(Color.BLACK);
        set1.setDrawCircles(false);
        set1.setLineWidth(2f);
        set1.setCircleRadius(3f);
        set1.setFillAlpha(255);
        set1.setDrawFilled(true);
        set1.setFillColor(Color.WHITE);
        set1.setDrawCircleHole(false);
        set1.setFillFormatter(new FillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return mChart.getAxisLeft().getAxisMinimum();
            }
        });

        // create a dataset and give it a type
        set2 = new LineDataSet(yVals2, "DataSet 2");
        set2.setAxisDependency(YAxis.AxisDependency.LEFT);
        set2.setColor(Color.BLACK);
        set2.setDrawCircles(false);
        set2.setLineWidth(2f);
        set2.setCircleRadius(3f);
        set2.setFillAlpha(255);
        set2.setDrawFilled(true);
        set2.setFillColor(Color.WHITE);
        set2.setDrawCircleHole(false);
        set2.setFillFormatter(new FillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return mChart.getAxisLeft().getAxisMaximum();
            }
        });

        set3 = new LineDataSet(yVals3, "DataSet 3");
        set3.setAxisDependency(YAxis.AxisDependency.LEFT);
        set3.setColor(Color.BLACK);
        set3.setDrawCircles(false);
        set3.setLineWidth(2f);
        set3.setCircleRadius(3f);
        set3.setFillAlpha(255);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(set1); // add the datasets
        dataSets.add(set2);
        dataSets.add(set3);

        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < yVals1.size(); i++) {
            strings.add(i + "");
        }

        // create a data object with the datasets
        LineData data = new LineData(strings , dataSets);
        data.setDrawValues(false);
        return data;
    }


}
