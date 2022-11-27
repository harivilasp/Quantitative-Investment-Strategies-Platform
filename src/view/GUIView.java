package view;

import java.util.List;
import java.util.Map;

public interface GUIView {
  void showPortfolioValue();

  void showSavePortfolio();

  void showAddPortfolio();

  void showLoadPortfolio();

  void showBuySellStock();

  void showCostBasis();

  void showCompositionAtDate(List<String> composition);

  void showAddStrategy();

  void showBuyStocksWithWeights();

  void showPerformance(Map<String, Integer> values);
  void showHome();
}
