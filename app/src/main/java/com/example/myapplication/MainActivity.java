package com.example.myapplication;

import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.Viewport;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Random;

import static java.lang.Thread.*;

public class MainActivity extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;
    public Handler mHandler = new Handler();
    private Random rand = new Random();
    public GraphView Graph;
    int x = 2;
    int y = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GraphView Graph = findViewById(R.id.Graph);
        Viewport viewport=Graph.getViewport();
        viewport.setYAxisBoundsManual(true);
        viewport.setMinY(0);
        viewport.setMaxY(10);
        viewport.setMinX(0);
        viewport.setMaxX(100);
        viewport.setScalable(true);
        viewport.setScrollable(true);

        series = new LineGraphSeries<DataPoint>();
        Graph.addSeries(series);
        Run();
    }
    public void Run()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<100;i++)
                {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        series.appendData(new DataPoint(x++,rand.nextInt()),true,100);
                    }
                });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
