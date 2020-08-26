package com.example.app;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.flow.component.charts.Chart;
import com.vaadin.flow.component.charts.model.ChartType;
import com.vaadin.flow.component.charts.model.Configuration;
import com.vaadin.flow.component.charts.model.DataSeries;
import com.vaadin.flow.component.charts.model.DataSeriesItemBullet;
import com.vaadin.flow.component.charts.model.PlotBand;
import com.vaadin.flow.component.charts.model.PlotOptionsBullet;
import com.vaadin.flow.component.charts.model.YAxis;
import com.vaadin.flow.component.charts.model.style.SolidColor;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and use @Route
 * annotation to announce it in a URL as a Spring managed bean.
 * <p>
 * A new instance of this class is created for every new user and every browser
 * tab/window.
 * <p>
 * The main view contains a text field for getting the user name and a button
 * that shows a greeting message in a notification.
 */
@Route
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
@CssImport(value = "./styles/vaadin-chart-styles.css", themeFor = "vaadin-chart", include = "vaadin-chart-default-theme")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service
     *            The message service. Automatically injected Spring managed
     *            bean.
     */
    public MainView(@Autowired GreetService service) {
      // Create a bullet chart
      Chart chart = new Chart(ChartType.BULLET);
      chart.setHeight("115px");
      // Modify the default configuration
      Configuration conf = chart.getConfiguration();
      conf.getChart().setStyledMode(true);
      conf.setTitle("2020 YTD");
      conf.getChart().setInverted(true);
      conf.getLegend().setEnabled(false);
      conf.getTooltip().setPointFormat("<b>{point.y}</b> (with target at {point.target})");

      // Add data
      PlotOptionsBullet options = new PlotOptionsBullet();
//      options.setPointPadding(0.25);
//      options.setBorderWidth(0);
//      options.setColor(SolidColor.BLACK);
      options.getTargetOptions().setWidth("200%");
      DataSeries series = new DataSeries();
      series.add(new DataSeriesItemBullet(275, 250));
      series.setPlotOptions(options);
      conf.addSeries(series);

      // Configure the axes
      YAxis yAxis = conf.getyAxis();
//      yAxis.setGridLineWidth(0);
      yAxis.setTitle("");
//      yAxis.addPlotBand(new PlotBand(0, 150, new SolidColor("#666666")));
//      yAxis.addPlotBand(new PlotBand(150, 225, new SolidColor("#999999")));
//      yAxis.addPlotBand(new PlotBand(225, 9e9, new SolidColor("#bbbbbb")));
      PlotBand band0 = new PlotBand();
      band0.setFrom(0);
      band0.setTo(150);
      band0.setClassName("color-0");
      PlotBand band1 = new PlotBand();
      band1.setFrom(150);
      band1.setTo(225);
      band1.setClassName("color-1");
      PlotBand band2 = new PlotBand();
      band2.setFrom(225);
      band2.setTo(9e9);
      band2.setClassName("color-2");
      yAxis.setPlotBands(band0, band1, band2);
      conf.getxAxis().addCategory("Revenue U.S. $ (1,000s)");

      add(chart);
    }

}
