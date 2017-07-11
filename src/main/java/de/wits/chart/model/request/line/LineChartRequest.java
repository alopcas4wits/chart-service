package de.wits.chart.model.request.line;


import de.wits.chart.model.DataUnit;
import de.wits.chart.model.request.GenericXYChartRequest;

import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 25.11.2016.
 */
public class LineChartRequest extends GenericXYChartRequest {
    private boolean drawAreas, stack;

    private Map<String, List<DataUnit>> data;

    public Map<String, List<DataUnit>> getData() {
        return data;
    }

    public void setData(Map<String, List<DataUnit>> data) {
        this.data = data;
    }

    public boolean isDrawAreas() {
        return drawAreas;
    }

    public void setDrawAreas(boolean drawAreas) {
        this.drawAreas = drawAreas;
    }

    public boolean isStack() {
        return stack;
    }

    public void setStack(boolean stack) {
        this.stack = stack;
    }
}
