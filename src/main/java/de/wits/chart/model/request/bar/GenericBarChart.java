package de.wits.chart.model.request.bar;

import de.wits.chart.model.request.GenericXYChartRequest;

import java.util.Map;

/**
 * Created by alberto on 12.04.17.
 */
public class GenericBarChart extends GenericXYChartRequest {
    private Map<String, Map<String, String>> middleLabels;
    private int middleLabelRotation, middleLabelSize;

    public int getMiddleLabelRotation() {
        return middleLabelRotation;
    }

    public void setMiddleLabelRotation(int middleLabelRotation) {
        this.middleLabelRotation = middleLabelRotation;
    }

    public int getMiddleLabelSize() {
        return middleLabelSize;
    }

    public void setMiddleLabelSize(int middleLabelSize) {
        this.middleLabelSize = middleLabelSize;
    }

    public Map<String, Map<String, String>> getMiddleLabels() {
        return middleLabels;
    }

    public void setMiddleLabels(Map<String, Map<String, String>> middleLabels) {
        this.middleLabels = middleLabels;
    }
}
