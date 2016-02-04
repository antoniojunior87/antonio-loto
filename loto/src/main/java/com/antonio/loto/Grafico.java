package com.antonio.loto;

import java.awt.Color;
import java.awt.Dimension;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class Grafico extends ApplicationFrame {

	private static final long serialVersionUID = 4458165669434364992L;

	public static void plotarGrafico(int[] valores) {
		final Grafico demo = new Grafico("Mega Sena", valores);
		demo.pack();
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
	}

	public Grafico(String title, int[] valores) {
		super(title);

		final CategoryDataset dataset = createDataset(valores);
		final JFreeChart chart = createChart(dataset);
		final ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new Dimension(1200, 600));
		setContentPane(chartPanel);
	}

	private CategoryDataset createDataset(int[] valores) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (int i = 0; i < valores.length; i++) {
			dataset.addValue(valores[i], Integer.valueOf(i + 1), Integer.valueOf(i + 1));
		}

		return dataset;
	}

	private JFreeChart createChart(final CategoryDataset dataset) {

		final JFreeChart chart = ChartFactory.createBarChart("Mega Sena", "Dezena", "Quantidade", dataset,
				PlotOrientation.VERTICAL, true, true, false);

		chart.setBackgroundPaint(Color.white);

		return chart;
	}

}
