package view;

import java.util.List;
import java.util.Map;

import controller.Features;

public interface GUIView {
  void showPortfolioValue(String portfolioName);

  void showSavePortfolio();

  void showAddPortfolio();

  void showLoadPortfolio();

  void showBuySellStock(String portfolioName);

  void showCostBasis(String portfolioName);

  void showCompositionAtDate(String portfolioName, List<String> composition);

  void showAddStrategy(String portfolioName);

  void showBuyStocksWithWeights();

  void showPerformance(Map<String, Integer> values);
  void showHome();
  public void addActionListener(Features features);
}
