package de.wits.chart.controller;

import com.google.gson.Gson;
import de.wits.chart.model.request.bar.ComplexBarChartRequest;
import de.wits.chart.model.request.bar.SimpleBarChartRequest;
import de.wits.chart.model.request.line.LineChartRequest;
import de.wits.chart.model.request.pie.PieChartRequest;
import de.wits.chart.service.ChartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

/**
 * Created by alberto on 09.03.17.
 */
@RestController
public class ChartController {

    ChartService chartService;

    @Autowired
    public ChartController(ChartService chartService) {
        this.chartService = chartService;
    }

    static {
        Authenticator.setDefault(new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("de\\hoist", "0!stErw1jk-NL".toCharArray());
            }
        });
    }

    private static final Logger LOG = LoggerFactory.getLogger(ChartController.class);

    @RequestMapping(value = "/pie", method = RequestMethod.POST, consumes = "text/plain;charset=UTF-8")
    public ResponseEntity<byte[]> getPieChart(@RequestBody String req) {
        LOG.debug("Building pie chart using the data...");
        PieChartRequest request = new Gson().fromJson(req, PieChartRequest.class);
        byte[] chart;

        try {
            chart = chartService.generatePieChart(request).get();
        } catch (Exception e) {
            LOG.error("Exception occurred while building the chart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @RequestMapping(value = "/line", method = RequestMethod.POST, consumes = "text/plain;charset=UTF-8")
    public ResponseEntity<byte[]> getLineChart(@RequestBody String req) {
        LOG.debug("Building Line chart using the data...");
        LineChartRequest request = new Gson().fromJson(req, LineChartRequest.class);
        byte[] chart;

        try {
            chart = chartService.generateLineChart(request).get();
        } catch (Exception e) {
            LOG.error("Exception occurred while building the chart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @RequestMapping(value = "/bar/simple", method = RequestMethod.POST, consumes = "text/plain;charset=UTF-8")
    public ResponseEntity<byte[]> getSimpleBarChart(@RequestBody String req) {
        LOG.debug("Building simple bar chart using the data...");
        SimpleBarChartRequest request = new Gson().fromJson(req, SimpleBarChartRequest.class);
        byte[] chart;

        try {
            chart = chartService.generateSimpleBarChart(request).get();
        } catch (Exception e) {
            LOG.error("Exception occurred while building the chart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @RequestMapping(value = "/bar/complex", method = RequestMethod.POST, consumes = "text/plain;charset=UTF-8")
    public ResponseEntity<byte[]> getComplexBarChart(@RequestBody String req) {

        LOG.info("Building complex bar chart using the data...");
        ComplexBarChartRequest request = new Gson().fromJson(req, ComplexBarChartRequest.class);
        byte[] chart;

        try {
            chart = chartService.generateComplexBarChart(request).get();
        } catch (Exception e) {
            LOG.error("Exception occurred while building the chart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(chart, HttpStatus.OK);
    }

    @RequestMapping(value = "/bar/stacked", method = RequestMethod.POST, consumes = "text/plain;charset=UTF-8")
    public ResponseEntity<byte[]> getStackedBarChart(@RequestBody String req) {

        LOG.info("Building stacked bar chart using the data...");
        ComplexBarChartRequest request = new Gson().fromJson(req, ComplexBarChartRequest.class);
        byte[] chart;

        try {
            chart = chartService.generateStackedBarChart(request).get();
        } catch (Exception e) {
            LOG.error("Exception occurred while building the chart", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(chart, HttpStatus.OK);
    }
}
