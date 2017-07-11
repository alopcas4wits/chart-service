package de.wits.chart;

import com.google.gson.Gson;
import de.wits.chart.model.request.bar.ComplexBarChartRequest;
import de.wits.chart.model.request.bar.SimpleBarChartRequest;
import de.wits.chart.model.request.line.LineChartRequest;
import de.wits.chart.model.request.pie.PieChartRequest;
import de.wits.chart.service.ChartService;
import javafx.application.Platform;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static org.junit.Assert.fail;

/**
 * Created by Jonas on 28.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ChartApplication.class)
public class ChartApplicationTest {

    @Autowired
    ChartService chartService;

    @Test
    public void contextLoads() {
    }

    @AfterClass
    public static void tearDown() {
        Platform.exit();
    }

    @Test
    public void pieChartTest() {
        String jsonInput = "{\"title\":\"Michelin\",\"showLegend\":true,\"legendSide\":\"BOTTOM\",\"label\":{\"Michelinprofiltyp1\":\"20 (12%)\",\"Michelinprofiltyp2\":\"50 (28%)\",\"Michelinprofiltyp3\":\"30 (16%)\",\"Michelinprofiltyp4\":\"70 (39%)\",\"Michelinprofiltyp5\":\"10 (5%)\"},\"data\":[{\"name\":\"Michelinprofiltyp1\",\"value\":20},{\"name\":\"Michelinprofiltyp2\",\"value\":50},{\"name\":\"Michelinprofiltyp3\",\"value\":30},{\"name\":\"Michelinprofiltyp4\",\"value\":70},{\"name\":\"Michelinprofiltyp5\",\"value\":10}]}";
        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generatePieChart(new Gson().fromJson(jsonInput, PieChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }
        Assert.assertFalse(chartData.length == 0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("/home/alberto/Desktop/test25.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(chartData);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void lineChartTest() {
        String jsonInput = "{\"title\":\"Entwicklung\",\"showLegend\":true,\"numericXAxis\":true,\"xAxisUpperBound\":12,\"xAxisLowerBound\":1,\"width\":800,\"height\":350,\"legendSide\":\"BOTTOM\",\"data\":{\"2011\":[{\"name\":\"1\",\"value\":34},{\"name\":\"2\",\"value\":18},{\"name\":\"3\",\"value\":16},{\"name\":\"4\",\"value\":51},{\"name\":\"5\",\"value\":94},{\"name\":\"6\",\"value\":69},{\"name\":\"7\",\"value\":84},{\"name\":\"8\",\"value\":70},{\"name\":\"9\",\"value\":30},{\"name\":\"10\",\"value\":79},{\"name\":\"11\",\"value\":86},{\"name\":\"12\",\"value\":69}],\"Marke\":[{\"name\":\"1\",\"value\":84},{\"name\":\"2\",\"value\":43},{\"name\":\"3\",\"value\":49},{\"name\":\"4\",\"value\":97},{\"name\":\"5\",\"value\":114},{\"name\":\"6\",\"value\":150},{\"name\":\"7\",\"value\":80},{\"name\":\"8\",\"value\":91},{\"name\":\"9\",\"value\":73},{\"name\":\"10\",\"value\":151},{\"name\":\"11\",\"value\":173},{\"name\":\"12\",\"value\":87}],\"Deutschland\":[{\"name\":\"1\",\"value\":30},{\"name\":\"2\",\"value\":52},{\"name\":\"3\",\"value\":28},{\"name\":\"4\",\"value\":182},{\"name\":\"5\",\"value\":187},{\"name\":\"6\",\"value\":175},{\"name\":\"7\",\"value\":72},{\"name\":\"8\",\"value\":55},{\"name\":\"9\",\"value\":29},{\"name\":\"10\",\"value\":101},{\"name\":\"11\",\"value\":146},{\"name\":\"12\",\"value\":72}]}}";

        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generateLineChart(new Gson().fromJson(jsonInput, LineChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }

        Assert.assertFalse(chartData.length == 0);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File("/home/alberto/Desktop/test2334.png"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            fos.write(chartData);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void stackedLineChartTest() {
        String jsonInput = "{\"stack\":true,\"showLegend\":true,\"legendSide\":\"BOTTOM\",\"yAxisUpperBound\":255,\"data\":{\"Überlagerung\":[{\"name\":\"1\",\"value\":30},{\"name\":\"2\",\"value\":52},{\"name\":\"3\",\"value\":28},{\"name\":\"4\",\"value\":182},{\"name\":\"5\",\"value\":80},{\"name\":\"6\",\"value\":50},{\"name\":\"7\",\"value\":29},{\"name\":\"8\",\"value\":55},{\"name\":\"9\",\"value\":29},{\"name\":\"10\",\"value\":50},{\"name\":\"11\",\"value\":40},{\"name\":\"12\",\"value\":27}],\"Auslagerung\":[{\"name\":\"1\",\"value\":34},{\"name\":\"2\",\"value\":18},{\"name\":\"3\",\"value\":16},{\"name\":\"4\",\"value\":51},{\"name\":\"5\",\"value\":94},{\"name\":\"6\",\"value\":69},{\"name\":\"7\",\"value\":84},{\"name\":\"8\",\"value\":70},{\"name\":\"9\",\"value\":30},{\"name\":\"10\",\"value\":44},{\"name\":\"11\",\"value\":33},{\"name\":\"12\",\"value\":13}],\"Einlagerung\":[{\"name\":\"1\",\"value\":84},{\"name\":\"2\",\"value\":43},{\"name\":\"3\",\"value\":49},{\"name\":\"4\",\"value\":12},{\"name\":\"5\",\"value\":25},{\"name\":\"6\",\"value\":32},{\"name\":\"7\",\"value\":80},{\"name\":\"8\",\"value\":91},{\"name\":\"9\",\"value\":25},{\"name\":\"10\",\"value\":56},{\"name\":\"11\",\"value\":43},{\"name\":\"12\",\"value\":31}]}}";

        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generateLineChart(new Gson().fromJson(jsonInput, LineChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }

        Assert.assertFalse(chartData.length == 0);
    }

    @Test
    public void areaChartTest() {
        String jsonInput = "{\"title\":\"test bar chart\", \"drawAreas\": true, \"yAxis\":\"sales\",\"xAxis\":\"year\",\"data\":{\"Austria\":[{\"name\":\"1\",\"value\":123},{\"name\":\"2\",\"value\":213},{\"name\":\"3\",\"value\":224},{\"name\":\"4\",\"value\":213},{\"name\":\"5\",\"value\":224},{\"name\":\"6\",\"value\":213},{\"name\":\"7\",\"value\":224},{\"name\":\"8\",\"value\":213},{\"name\":\"9\",\"value\":224},{\"name\":\"10\",\"value\":213},{\"name\":\"11\",\"value\":224},{\"name\":\"12\",\"value\":213}],\"Germany\":[{\"name\":\"1\",\"value\":223},{\"name\":\"2\",\"value\":313},{\"name\":\"3\",\"value\":24},{\"name\":\"4\",\"value\":13},{\"name\":\"5\",\"value\":424},{\"name\":\"6\",\"value\":94},{\"name\":\"7\",\"value\":124},{\"name\":\"8\",\"value\":113},{\"name\":\"9\",\"value\":324},{\"name\":\"10\",\"value\":113},{\"name\":\"11\",\"value\":24},{\"name\":\"12\",\"value\":353}]}}";

        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generateLineChart(new Gson().fromJson(jsonInput, LineChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }

        Assert.assertFalse(chartData.length == 0);
    }

    @Test
    public void simpleBarChartTest() {
        String jsonInput = "{\"title\":\"Winter\",\"data\":[{\"name\":\"1\",\"value\":5},{\"name\":\"2\",\"value\":140},{\"name\":\"3\",\"value\":50},{\"name\":\"5\",\"value\":190},{\"name\":\"20\",\"value\":137},{\"name\":\"23\",\"value\":174}]}";

        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generateSimpleBarChart(new Gson().fromJson(jsonInput, SimpleBarChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }

        Assert.assertFalse(chartData.length == 0);
    }

    @Test
    public void complexBarChartTest() {
        String jsonInput = "{\"title\":\"Überlagerungen\",\"showLegend\":true,\"legendSide\":\"BOTTOM\",\"data\":{\"2011\":[{\"name\":\"31.01\",\"value\":223},{\"name\":\"30.07\",\"value\":178}],\"2012\":[{\"name\":\"31.01\",\"value\":143},{\"name\":\"30.07\",\"value\":118}],\"2013\":[{\"name\":\"31.01\",\"value\":172},{\"name\":\"30.07\",\"value\":178}],\"2014\":[{\"name\":\"31.01\",\"value\":192},{\"name\":\"30.07\",\"value\":188}]}}";

        byte[] chartData = new byte[0];
        try {
            chartData = chartService.generateComplexBarChart(new Gson().fromJson(jsonInput, ComplexBarChartRequest.class)).get();
        } catch (Exception e) {
            e.printStackTrace();
            fail("No exceptions should be thrown");
        }
        Assert.assertFalse(chartData.length == 0);
    }
}
