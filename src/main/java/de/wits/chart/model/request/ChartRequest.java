package de.wits.chart.model.request;

import javafx.geometry.Side;

import java.util.UUID;

/**
 * Created by Admin on 25.11.2016.
 */
public class ChartRequest {
    private String title;

    private UUID id;

    private boolean showLegend;

    private int width;
    private int height;

    private double legendSize;

    private Side legendSide;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShowLegend() {
        return showLegend;
    }

    public void setShowLegend(boolean showLegend) {
        this.showLegend = showLegend;
    }

    public Side getLegendSide() {
        return legendSide;
    }

    public void setLegendSide(Side legendSide) {
        this.legendSide = legendSide;
    }

    public double getLegendSize() {
        return legendSize;
    }

    public void setLegendSize(double legendSize) {
        this.legendSize = legendSize;
    }
}
