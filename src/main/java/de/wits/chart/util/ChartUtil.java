package de.wits.chart.util;

import com.google.common.base.Strings;
import de.wits.chart.model.DataUnit;
import de.wits.chart.model.LabelPosition;
import de.wits.chart.model.chart.*;
import de.wits.chart.model.request.ChartRequest;
import de.wits.chart.model.request.GenericXYChartRequest;
import de.wits.chart.model.request.line.LineChartRequest;
import de.wits.chart.model.request.pie.PieChartRequest;
import de.wits.chart.service.ChartServiceImpl;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by alberto on 07.03.17.
 */
public abstract class ChartUtil {

    final static double DEFAULT_HEIGHT = 800;
    final static double DEFAULT_WIDTH = 800;


    private static void initializeChart(ChartRequest request, Chart c) {
        c.setAnimated(false);
        if (!Strings.isNullOrEmpty(request.getTitle())) {
            c.setTitle(request.getTitle());
        }
        if (request.getWidth() > 0 && request.getHeight() > 0) {
            c.setPrefSize(request.getWidth(), request.getHeight());
        } else {
            c.setPrefSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        }
        c.setLegendVisible(request.isShowLegend());
        c.setLegendSide(request.getLegendSide());
    }

    public static DoughnutChart initializePieChart(PieChartRequest request) {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (DataUnit data : request.getData()) {
            pieChartData.add(new javafx.scene.chart.PieChart.Data(data.getName(), data.getValue()));
        }
        final DoughnutChart chart = new DoughnutChart(pieChartData);
        if (request.getLegendSize() > 0) {
            chart.setLegendSize(request.getLegendSize());
        }
        initializeChart(request, chart);
        return chart;
    }

    public static XYChart initializeGenericXYChart(GenericXYChartRequest request, Class chartType) {
        Axis xAxis;
        Axis yAxis = new NumberAxis();
        if (request.isNumericXAxis()) {
            xAxis = new NumberAxis();
        } else {
            xAxis = new CategoryAxis();
        }

        if (!Strings.isNullOrEmpty(request.getxAxis())) {
            xAxis.setLabel(request.getxAxis());
        }
        if (!Strings.isNullOrEmpty(request.getyAxis())) {
            yAxis.setLabel(request.getyAxis());
        }
        if (request.getAxisSize() > 0) {
            xAxis.tickLabelFontProperty().set(Font.font(request.getAxisSize()));
            yAxis.tickLabelFontProperty().set(Font.font(request.getAxisSize()));
        }

        // Custom axis bounds
        if (xAxis instanceof NumberAxis && request.getxAxisUpperBound() != request.getxAxisLowerBound()) {
            NumberAxis tmpXAxis = (NumberAxis) xAxis;
            tmpXAxis.setAutoRanging(false);
            tmpXAxis.setUpperBound(request.getxAxisUpperBound());
            tmpXAxis.setLowerBound(request.getxAxisLowerBound());
            tmpXAxis.setTickUnit(request.getxAxisUpperBound() > 10 ? request.getxAxisUpperBound() / 10 : 1);
        }

        if (yAxis instanceof NumberAxis && request.getyAxisUpperBound() != request.getyAxisLowerBound()) {
            NumberAxis tmpYAxis = (NumberAxis) yAxis;
            tmpYAxis.setAutoRanging(false);
            tmpYAxis.setUpperBound(request.getyAxisUpperBound());
            tmpYAxis.setLowerBound(request.getyAxisLowerBound());
            tmpYAxis.setTickUnit(request.getyAxisUpperBound() > 10 ? request.getyAxisUpperBound() / 10 : 1);
        }

        XYChart c = null;
        try {
            c = (XYChart) chartType.getConstructor(Axis.class, Axis.class).newInstance(xAxis, yAxis);

            if (request.getLegendSize() > 0) {
                try {
                    c.getClass().getMethod("setLegendSize", double.class).invoke(c, request.getLegendSize());
                } catch (Exception e) {
                    
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error happened.", e);
        }
        initializeChart(request, c);

        return c;
    }

    public static XYChart initializeLineChart(LineChartRequest request) {
        Class clazz;
        if (request.isDrawAreas()) {
            if (request.isStack()) {
                clazz = ExtendedStackedAreaChart.class;
            } else {
                clazz = ExtendedAreaChart.class;
            }
        } else {
            clazz = ExtendedLineChart.class;
        }

        return initializeGenericXYChart(request, clazz);
    }

    static final Logger LOG = LoggerFactory.getLogger(ChartServiceImpl.class);

    public static BarChart initializeBarChart(GenericXYChartRequest request) {
        ExtendedBarChart barChart = (ExtendedBarChart) initializeGenericXYChart(request, ExtendedBarChart.class);
        barChart.setBarGap(10);
        barChart.setCategoryGap(80);

        return barChart;
    }

    public static StackedBarChart initializeStackedBarChart(GenericXYChartRequest request) {
        StackedBarChart barChart = (StackedBarChart) initializeGenericXYChart(request, StackedBarChart.class);
        barChart.setCategoryGap(80);

        return barChart;
    }

    public static void displayLabel(XYChart.Data data, String topLabel, int topLabelSize, int topLabelRotation, final LabelPosition position) {
        final Node node = data.getNode();
        Group parentGroup = (Group) node.getParent();

        if (!Strings.isNullOrEmpty(topLabel)) {
            final Text dataText = new Text(topLabel);
            if (topLabelSize != 0) {
                dataText.fontProperty().setValue(Font.font(topLabelSize));
            }
            if (topLabelRotation != 0) {
                dataText.setRotate(topLabelRotation);
            }
            parentGroup.getChildren().add(dataText);
            node.boundsInParentProperty().addListener(new ChangeListener<Bounds>() {
                @Override
                public void changed(ObservableValue<? extends Bounds> ov, Bounds oldBounds, Bounds bounds) {
                    dataText.setLayoutX(
                            Math.round(
                                    bounds.getMinX() + bounds.getWidth() / 2 - dataText.prefWidth(-1) / 2
                            )
                    );

                    if (position == LabelPosition.MIDDLE) {
                        dataText.setLayoutY(Math.round(((bounds.getMaxY() + bounds.getMinY()) / 2 - dataText.prefHeight(-1) * 0.5)));
                    } else if (position == LabelPosition.TOP) {
                        dataText.setLayoutY(Math.round(bounds.getMinY() - dataText.prefHeight(-1) * 0.5));
                    }
                }
            });
        }
    }
}
