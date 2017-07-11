package de.wits.chart.model;

/**
 * Created by Admin on 25.11.2016.
 */
public class DataUnit {

    public DataUnit() {
    }

    public DataUnit(String name, float value) {
        this.name = name;
        this.value = value;
    }

    private String name;
    private float value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
