package view;

import java.util.List;
import java.util.Map;

import controller.Features;

public interface GUIView {

  void showPortfolioValue(String portfolioName);

  void showSavePortfolio(String portfolioName);

  void showAddPortfolio();

  void showLoadPortfolio();

  void showBuySellStock(String portfolioName);

  void showCostBasis(String portfolioName);

  void showCompositionAtDate(String portfolioName, List<String> composition);

  void showAddStrategy(String portfolioName);

  void showBuyStocksWithWeights(String portfolioName);

  void showPerformance(Map<String, Integer> values);

  void showHome(String portfolioName);

  void addActionListener(Features features);

  void showInputPerformanceDates(String portfolioName);
}
