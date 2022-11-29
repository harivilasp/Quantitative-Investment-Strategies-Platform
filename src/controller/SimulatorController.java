package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Simulator;
import model.Stock;
import view.GUIView;

/**
 * This is the simulator GUI controller implementation which handles flow of program and
 * implementation of {@code Features} interface.
 */
public class SimulatorController implements Features {

  /* The model class instance. */
  private final Simulator model;

  /* The view class instance. */
  private GUIView view;

  /* The scanner class instance. */
  private final Scanner scanner;

  /* The Appendable out instance. */
  private final Appendable out;

  /**
   * This method constructs the model of controller.
   *
   * @param model represents the model of program
   * @param view  represents the view of program
   * @param in    represents the Readable Input Stream
   * @param out   represents the Appendable Output Stream
   */
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
    // TODO: Complete implementation
  }

  @Override
  public String getValue(String date) {
    try {
      double valueAtDate = model.getValue(date);
      return "Value at " + date + " : " + Double.toString(valueAtDate);
    } catch (Exception e) {
      return e.getMessage();
    }
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
  public String save() {
    try {
      model.save();
      return "Successfully Saved";
    } catch (Exception e) {
      return e.getMessage();
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
  public String loadFlexiblePortfolio(String filepath) {
    try {
      model.loadFlexiblePortfolio(filepath);
      return "Successfully Loaded: " + model.getName();
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @Override
  public String buyStock(String stockName, int stockQty, String date, double commission) {
    try {
      this.model.buyStock(stockName, stockQty, date, commission);
      this.model.save();
      return "Successfully Bought";
    } catch (Exception e) {
      return e.getMessage();
    }
  }

  @Override
  public String sellStock(String stockName, int stockQty, String date, double commission) {
    try {
      this.model.sellStock(stockName, stockQty, date, commission);
      this.model.save();
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
      return "Invalid Argument";
    }
  }

  @Override
  public List<String> getCompositionAtDate(String date) {
    List<Stock> stockList = new ArrayList<>();
    try {
      stockList = this.model.getCompositionAtDate(date);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
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
      return e.getMessage();
    }
  }

  @Override
  public Map<String, Integer> getPerformance(String startDate, String endDate) {
    Map<String, Integer> stringIntegerMap = new HashMap<>();
    try {
      stringIntegerMap = model.getPerformance(startDate, endDate);
      // todo
      return stringIntegerMap;
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
    view.showHome(this.getName());
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
    view.showSavePortfolio(this.getName());
  }

  @Override
  public void showReadPortfolio() {
    view.showLoadPortfolio();
  }

  @Override
  public void showCreateStrategy() {
    view.showAddStrategy(this.getName());
  }

  public void showInputPerformanceDates() {
    view.showInputPerformanceDates(this.getName());
  }

  public void showInvestAmount() {
    view.showBuyStocksWithWeights(this.getName());
  }

}
