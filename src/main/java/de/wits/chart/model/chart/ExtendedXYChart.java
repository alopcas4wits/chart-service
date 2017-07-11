package de.wits.chart.model.chart;

import com.sun.javafx.charts.Legend;
import javafx.scene.chart.Axis;
import javafx.scene.chart.XYChart;

/**
 * Created by alberto on 12.06.17.
 */
public abstract class ExtendedXYChart extends XYChart {
    /**
     * Constructs a XYChart given the two axes. The initial content for the chart
     * plot background and plot area that includes vertical and horizontal grid
     * lines and fills, are added.
     *
     * @param axis  X Axis for this XY chart
     * @param axis2 Y Axis for this XY chart
     */
    public ExtendedXYChart(Axis axis, Axis axis2) {
        super(axis, axis2);
    }

    public void setLegendSize(double newSize) {
        ((Legend) getLegend()).setScaleX(newSize);
        ((Legend) getLegend()).setScaleY(newSize);
        ((Legend) getLegend()).setMaxWidth(getWidth() - (getWidth() * newSize)); // TODO FIXME
    }
}
