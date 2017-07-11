package de.wits.chart.model.request.pie;


import de.wits.chart.model.DataUnit;
import de.wits.chart.model.request.ChartRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 25.11.2016.
 */
public class PieChartRequest extends ChartRequest {

    private List<DataUnit> data;
    private Map<String, String> label;
    private int labelSize;

    public PieChartRequest() {
    }

    public List<DataUnit> getData() {
        return data;
    }

    public void setData(List<DataUnit> data) {
        this.data = data;
    }

    public Map<String, String> getLabel() {
        return label;
    }

    public void setLabel(Map<String, String> label) {
        this.label = label;
    }

    public int getLabelSize() {
        return labelSize;
    }

    public void setLabelSize(int labelSize) {
        this.labelSize = labelSize;
    }
}
