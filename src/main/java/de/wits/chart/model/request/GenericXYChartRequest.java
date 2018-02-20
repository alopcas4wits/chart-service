package de.wits.chart.model.request;

import java.util.Map;

/**
 * Created by Admin on 25.11.2016.
 */
public class GenericXYChartRequest extends ChartRequest {

    private String xAxis;
    private String yAxis;
    private int yAxisUpperBound, yAxisLowerBound, xAxisUpperBound, xAxisLowerBound;

    private int axisSize;
    private boolean numericXAxis;

    private Map<String, Map<String, String>> topLabels;
    private int topLabelRotation, topLabelSize;

    public String getxAxis() {
        return xAxis;
    }

    public void setxAxis(String xAxis) {
        this.xAxis = xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public void setyAxis(String yAxis) {
        this.yAxis = yAxis;
    }

    public int getAxisSize() {
        return axisSize;
    }

    public void setAxisSize(int axisSize) {
        this.axisSize = axisSize;
    }

    public int getTopLabelSize() {
        return topLabelSize;
    }

    public void setTopLabelSize(int topLabelSize) {
        this.topLabelSize = topLabelSize;
    }

    public int getTopLabelRotation() {
        return topLabelRotation;
    }

    public void setTopLabelRotation(int topLabelRotation) {
        this.topLabelRotation = topLabelRotation;
    }

    public boolean isNumericXAxis() {
        return numericXAxis;
    }

    public void setNumericXAxis(boolean numericXAxis) {
        this.numericXAxis = numericXAxis;
    }

    public Map<String, Map<String, String>> getTopLabels() {
        return topLabels;
    }

    public void setTopLabels(Map<String, Map<String, String>> topLabels) {
        this.topLabels = topLabels;
    }

    public int getyAxisLowerBound() {
        return yAxisLowerBound;
    }

    public void setyAxisLowerBound(int yAxisLowerBound) {
        this.yAxisLowerBound = yAxisLowerBound;
    }

    public int getyAxisUpperBound() {
        return yAxisUpperBound;
    }

    public void setyAxisUpperBound(int yAxisUpperBound) {
        this.yAxisUpperBound = (int) Math.ceil((yAxisUpperBound / 100d)) * 100;
    }

    public int getxAxisUpperBound() {
        return xAxisUpperBound;
    }

    public void setxAxisUpperBound(int xAxisUpperBound) {
        this.xAxisUpperBound = xAxisUpperBound;
    }

    public int getxAxisLowerBound() {
        return xAxisLowerBound;
    }

    public void setxAxisLowerBound(int xAxisLowerBound) {
        this.xAxisLowerBound = xAxisLowerBound;
    }
}
