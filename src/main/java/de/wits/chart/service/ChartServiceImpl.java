package de.wits.chart.service;

import com.google.common.base.Strings;
import de.wits.chart.model.DataUnit;
import de.wits.chart.model.LabelPosition;
import de.wits.chart.model.request.GenericXYChartRequest;
import de.wits.chart.model.request.bar.ComplexBarChartRequest;
import de.wits.chart.model.request.bar.GenericBarChart;
import de.wits.chart.model.request.bar.SimpleBarChartRequest;
import de.wits.chart.model.request.line.LineChartRequest;
import de.wits.chart.model.request.pie.PieChartRequest;
import de.wits.chart.util.ChartUtil;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by alberto on 08.03.17.
 */
@Service
public class ChartServiceImpl extends Application implements ChartService {

    static Stage stage;


    static final Logger LOG = LoggerFactory.getLogger(ChartServiceImpl.class);

    public ChartServiceImpl() {
    }

    @PostConstruct
    public void initialize() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    launch(ChartServiceImpl.class);
                } catch (Exception e) {
                    LOG.error("ERROR", e);
                }
            }
        }).start();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
    }

    @Override
    @Async
    public Future<byte[]> generatePieChart(PieChartRequest request) throws IOException, ExecutionException, InterruptedException {
        Scene scene = new Scene(new Group());

        PieChart chart = ChartUtil.initializePieChart(request);

        if (request.getLabel() != null && request.getLabel().size() > 0) {
            chart.getData().forEach(d -> {
                Optional<Node> opTextNode = chart.lookupAll(".chart-pie-label").stream().filter(n -> n instanceof Text && ((Text) n).getText().contains(d.getName())).findAny();
                if (opTextNode.isPresent()) {
                    String res = request.getLabel().get(d.getName());
                    if (!Strings.isNullOrEmpty(res)) {
                        ((Text) opTextNode.get()).setText(res);
                        if (request.getLabelSize() > 0) {
                            ((Text) opTextNode.get()).fontProperty().setValue(Font.font(request.getLabelSize()));
                        }
                    }
                }
            });
        }

        ((Group) scene.getRoot()).getChildren().add(chart);

        return new AsyncResult<byte[]>(snapScene(scene).get());
    }

    @Override
    @Async
    public Future<byte[]> generateLineChart(LineChartRequest request) throws IOException, ExecutionException, InterruptedException {
        Scene scene = new Scene(new Group());

        final XYChart<Number, Number> lineChart = ChartUtil.initializeLineChart(request);

        for (Map.Entry<String, List<DataUnit>> entry : request.getData().entrySet()) {
            XYChart.Series series = new XYChart.Series();
            series.setName(entry.getKey());
            for (DataUnit unit : entry.getValue()) {
                Object name = unit.getName();
                if (request.isNumericXAxis()) {
                    name = Float.parseFloat(unit.getName());
                }
                series.getData().add(new XYChart.Data(name, unit.getValue()));
            }

            lineChart.getData().add(series);
        }

        displayLabelsIfPossible(request, lineChart);


        ((Group) scene.getRoot()).getChildren().add(lineChart);

        return new AsyncResult<byte[]>(snapScene(scene).get());
    }


    @Override
    @Async
    public Future<byte[]> generateSimpleBarChart(SimpleBarChartRequest request) throws IOException, ExecutionException, InterruptedException {
        BarChart barChart = ChartUtil.initializeBarChart(request);
        Scene scene = new Scene(barChart);

        XYChart.Series tmpSeries = new XYChart.Series();
        request.getData().forEach(dataUnit -> {
            tmpSeries.getData().add(new XYChart.Data(dataUnit.getName(), dataUnit.getValue()));
        });
        barChart.getData().add(tmpSeries);

        // Set a different color for each column
        for (Object data : tmpSeries.getData()) {
            ((XYChart.Data) data).getNode().setStyle("-fx-bar-fill: CHART_COLOR_" + (tmpSeries.getData().indexOf(data) + 1));
        }

        displayLabelsIfPossible(request, barChart);
        return new AsyncResult<byte[]>(snapScene(scene).get());
    }

    @Override
    @Async
    public Future<byte[]> generateComplexBarChart(ComplexBarChartRequest request) throws IOException, ExecutionException, InterruptedException {
        LOG.info("Initializing barchart");
        BarChart barChart = ChartUtil.initializeBarChart(request);
        LOG.info("Creating scene");
        Scene scene = new Scene(barChart);

        LOG.info("Adding data to the chart");
        request.getData().forEach((s, dataUnits) -> {
            XYChart.Series<String, Number> tmpSeries = new XYChart.Series();
            tmpSeries.setName(s);
            dataUnits.forEach(dataUnit -> {
                tmpSeries.getData().add(new XYChart.Data(dataUnit.getName(), dataUnit.getValue()));
            });
            barChart.getData().add(tmpSeries);
        });

        displayLabelsIfPossible(request, barChart);

        LOG.info("Snapping scene");
        return new AsyncResult<byte[]>(snapScene(scene).get());
    }

    private void displayLabelsIfPossible(GenericXYChartRequest request, XYChart chart) {
        if (request.getTopLabels() != null || (request instanceof GenericBarChart && ((GenericBarChart) request).getMiddleLabels() != null)) {
            for (Object series : chart.getData()) {
                for (Object data : ((XYChart.Series) series).getData()) {
                    if (request.getTopLabels() != null && request.getTopLabels().keySet().contains(((XYChart.Series) series).getName().toString())) {
                        ChartUtil.displayLabel((XYChart.Data) data, request.getTopLabels().get(((XYChart.Series) series).getName()).get(String.valueOf(((XYChart.Data) data).getXValue())), request.getTopLabelSize(), request.getTopLabelRotation(), LabelPosition.TOP);
                    }
                    if (request instanceof GenericBarChart && ((GenericBarChart) request).getMiddleLabels() != null && ((GenericBarChart) request).getMiddleLabels().keySet().contains(((XYChart.Series) series).getName().toString())) {
                        ChartUtil.displayLabel((XYChart.Data) data, ((GenericBarChart) request).getMiddleLabels().get(((XYChart.Series) series).getName()).get(String.valueOf(((XYChart.Data) data).getXValue())), ((GenericBarChart) request).getMiddleLabelSize(), ((GenericBarChart) request).getMiddleLabelRotation(), LabelPosition.MIDDLE);
                    }
                }
            }
        }
    }

    public Future<byte[]> snapScene(final Scene scene) throws IOException {
        // Run on UI thread
        final WritableImage[] image = new WritableImage[1];
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.setScene(scene);
                image[0] = stage.getScene().snapshot(null);
            }
        });

        ByteArrayOutputStream s = new ByteArrayOutputStream();

        while (image[0] == null) {
            try {
                LOG.debug("Waiting for image...");
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException("Error while snapping the scene");
            }
        }

        ImageIO.write(SwingFXUtils.fromFXImage(image[0], null), "png", s);

        return new AsyncResult<byte[]>(s.toByteArray());
    }

}
