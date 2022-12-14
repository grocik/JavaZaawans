package com.example.demo.charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import java.io.IOException;

public class PieChartGenerator extends ChartGenerator implements IChartGenerator {
    private final DefaultPieDataset dataset = new DefaultPieDataset();

    public PieChartGenerator() {
    }


    public DefaultPieDataset getDataset() {
        return dataset;
    }

    public DefaultPieDataset setDataset() {
        return dataset;
    }

    public byte[] generate(String title, String type, String xAxisLabel, String yAxisLabel) throws IOException {
        JFreeChart chart = ChartFactory.createPieChart(
                title,
                dataset,
                true,
                true,
                false);
        return saveChart(title, chart, type);
    }
}
