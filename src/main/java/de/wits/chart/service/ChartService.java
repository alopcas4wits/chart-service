package de.wits.chart.service;

import de.wits.chart.model.request.bar.ComplexBarChartRequest;
import de.wits.chart.model.request.bar.SimpleBarChartRequest;
import de.wits.chart.model.request.line.LineChartRequest;
import de.wits.chart.model.request.pie.PieChartRequest;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by Admin on 25.11.2016.
 */
public interface ChartService {

    public Future<byte[]> generatePieChart(PieChartRequest request) throws IOException, ExecutionException, InterruptedException;

    public Future<byte[]> generateSimpleBarChart(SimpleBarChartRequest request) throws IOException, ExecutionException, InterruptedException;

    public Future<byte[]> generateComplexBarChart(ComplexBarChartRequest request) throws IOException, ExecutionException, InterruptedException;

    public Future<byte[]> generateLineChart(LineChartRequest request) throws IOException, ExecutionException, InterruptedException;

}
