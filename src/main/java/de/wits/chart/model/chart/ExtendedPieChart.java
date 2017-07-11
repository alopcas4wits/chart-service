package de.wits.chart.model.chart;

import com.sun.javafx.charts.Legend;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;

/**
 * Created by alberto on 12.06.17.
 */
public class ExtendedPieChart extends PieChart {

    public ExtendedPieChart(ObservableList<Data> pieChartData) {
        super(pieChartData);
    }

    public void setLegendSize(double newSize) {
        ((Legend) getLegend()).setScaleX(newSize);
        ((Legend) getLegend()).setScaleY(newSize);
        ((Legend) getLegend()).setMaxWidth(getWidth() - (getWidth() * newSize)); // TODO FIXME
    }
}
