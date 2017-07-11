package de.wits.chart.model.chart;

import com.sun.javafx.charts.Legend;
import javafx.collections.ObservableList;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.Axis;

/**
 * Created by alberto on 12.06.17.
 */
public class ExtendedAreaChart extends AreaChart {


    public ExtendedAreaChart(Axis axis, Axis axis2) {
        super(axis, axis2);
    }

    public ExtendedAreaChart(Axis axis, Axis axis2, ObservableList data) {
        super(axis, axis2, data);
    }

    public void setLegendSize(double newSize) {
        ((Legend) getLegend()).setScaleX(newSize);
        ((Legend) getLegend()).setScaleY(newSize);
        ((Legend) getLegend()).setMaxWidth(getWidth() - (getWidth() * newSize)); // TODO FIXME
    }
}
