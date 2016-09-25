package app.altum.growthappproto;

import android.content.Context;
import android.support.annotation.NonNull;

import com.github.mikephil.charting.data.Entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HansP on 24-09-2016.
 */

public class CsvReader {

    public List<List<Entry>> getMaleHeightData(Context context) {

        return parseCSV(context,R.raw.heightmale);
    }

    public List<List<Entry>> getMaleWeightData(Context context) {
        return parseCSV(context,R.raw.weightmale);
    }

    @NonNull
    private List<List<Entry>> parseCSV(Context context, int path) {
        InputStream xmlFileInputStream = context.getResources().openRawResource(path);
        List<Entry> lowerEntries = new ArrayList<>();
        List<Entry> highEntries = new ArrayList<>();
        List<Entry> avgEntries = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(xmlFileInputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] RowData = line.split(",");
                float x = Float.parseFloat(RowData[0]);
                float y = Float.parseFloat(RowData[1]);
                float z = Float.parseFloat(RowData[2]);
                float v = Float.parseFloat(RowData[3]);
                // do something with "data" and "value"
                lowerEntries.add(new Entry(y, (int) x));
                avgEntries.add(new Entry(z, (int) x));
                highEntries.add(new Entry(v, (int) x));
            }
        } catch (IOException ex) {
            // handle exception
        } finally {
            try {
                xmlFileInputStream.close();
            } catch (IOException e) {
                // handle exception
            }
        }
        List<List<Entry>> res = new ArrayList<>();
        res.add(lowerEntries);
        res.add(highEntries);
        res.add(avgEntries);
        return res;
    }




}
