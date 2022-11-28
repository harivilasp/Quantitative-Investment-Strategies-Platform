package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import model.Simulator;
import model.Stock;
import view.GUIView;

public class SimulatorController implements Features {

  /* The model class instance. */
  private final Simulator model;

  /* The view class instance. */
  private GUIView view;

  /* The scanner class instance. */
  private final Scanner scanner;

  /* The Appendable out instance. */
  private final Appendable out;

  public SimulatorController(Simulator model, GUIView view, Readable in, Appendable out) {
    // Create the model
    this.model = model;
    // Create the view
    this.view = view; // Inflexible portfolio
    // Instantiate the scanner
    this.scanner = new Scanner(in);
    // Instantiate the appendable out
    this.out = out;
    view.addActionListener(this);
  }

  @Override
  public boolean isPortfolioChosen() {
    return false;
  }

  @Override
  public void resetPortfolios() {

  }

  @Override
  public double getValue(String date) throws IllegalArgumentException, RuntimeException {
    return 0;
  }

  @Override
  public String getName() {
    if (model.isPortfolioChosen()) {
      return model.getName();
    } else {
      return "No Portfolio chosen";
    }
  }

  @Override
  public String save() throws IOException, RuntimeException {
    try {
      model.save();

      return "Successfully Saved";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String addFlexiblePortfolio(String portfolioName) {
    try {
      model.addFlexiblePortfolio(portfolioName);
      return "Successfully Created Portfolio";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String loadFlexiblePortfolio(String filepath)
      throws IllegalArgumentException, RuntimeException {
    try {
      model.loadFlexiblePortfolio(filepath);
      return "Successfully Loaded " + model.getName();
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String buyStock(String stockName, int stockQty, String date, double commission) {
    try {
      this.model.buyStock(stockName, stockQty, date, commission);
      return "Successfully Bought";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String sellStock(String stockName, int stockQty, String date, double commission) {
    try {
      this.model.sellStock(stockName, stockQty, date, commission);
      return "Successfully Sold";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @Override
  public String getCostBasis(String date) {
    try {
      double costBasis = this.model.getCostBasis(date);
      return "Cost Basis : " + costBasis;
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public List<String> getCompositionAtDate(String date) {
    List<Stock> stockList = this.model.getCompositionAtDate(date);
    List<String> list = new ArrayList<>();
    for (Stock stock : stockList) {
      list.add(stock.toString());
    }
    if (list.size() == 0) {
      list.add("No Stocks available at this date");
    }
    return list;
  }

  @Override
  public String addStrategy(double amount, int intervalInDays,
      String startDate, String endDate, double commission,
      Map<String, Double> weights) {
    try {
      this.model.addStrategy(amount, intervalInDays, startDate, endDate, commission, weights);
      return "Successfully applied";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public String buyStocksWithWeights(double amount, String date, double commission,
      Map<String, Double> weights) {
    try {
      this.model.buyStocksWithWeights(amount, date, commission, weights);
      return "Successfully bought";
    } catch (Exception e) {
      return e.toString();
    }
  }

  @Override
  public Map<String, Integer> getPerformance(String startDate, String endDate) {
    Map<String, Integer> stringIntegerMap = new HashMap<>();
    try {
      return getPerformance(startDate, endDate);
    } catch (Exception e) {
      stringIntegerMap.put(e.getMessage(), 1);
      return stringIntegerMap;
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void showHome() {
    view.showHome();
  }

  @Override
  public void showCreatePortfolio() {
    view.showAddPortfolio();
  }

  @Override
  public void showPortfolioComposition() {
    List<String> list = new ArrayList<>();
    view.showCompositionAtDate(this.getName(), list);
  }

  @Override
  public void showBuySellStock() {
    view.showBuySellStock(this.getName());
  }

  @Override
  public void showCostBasis() {
    view.showCostBasis(this.getName());
  }

  @Override
  public void showPortfolioValue() {
    view.showPortfolioValue(this.getName());
  }

  @Override
  public void showSavePortfolio() {
    view.showSavePortfolio();
  }

  @Override
  public void showReadPortfolio() {
    view.showLoadPortfolio();
  }

  @Override
  public void showCreateStrategy() {
    view.showAddStrategy(this.getName());
  }
}
