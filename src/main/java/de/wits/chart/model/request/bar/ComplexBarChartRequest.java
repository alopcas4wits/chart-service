package de.wits.chart.model.request.bar;

import de.wits.chart.model.DataUnit;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 25.11.2016.
 */
public class ComplexBarChartRequest extends GenericBarChart {
    private Map<String, List<DataUnit>> data;

    public Map<String, List<DataUnit>> getData() {
        return data;
    }

    public void setData(Map<String, List<DataUnit>> data) {
        this.data = data;
    }

}
