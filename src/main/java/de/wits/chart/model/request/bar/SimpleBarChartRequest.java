package de.wits.chart.model.request.bar;

import de.wits.chart.model.DataUnit;

import java.util.List;

/**
 * Created by Admin on 25.11.2016.
 */
public class SimpleBarChartRequest extends GenericBarChart {
    private List<DataUnit> data;

    public List<DataUnit> getData() {
        return data;
    }

    public void setData(List<DataUnit> data) {
        this.data = data;
    }

    /**
     * Simple bar charts do not come with a Legend
     *
     * @param isShowLegend
     */
    @Override
    public void setShowLegend(boolean isShowLegend) {
        super.setShowLegend(Boolean.FALSE);
    }
}
