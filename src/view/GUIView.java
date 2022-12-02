package view;

import controller.Features;
import java.util.List;

/**
 * This interface defines all the operations as part of the Graphical User Interface (GUI) for the
 * user to create and work on Flexible portfolios.
 */
public interface GUIView {

  /**
   * Display the portfolio value view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showPortfolioValue(String portfolioName);

  /**
   * Display the save portfolio view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showSavePortfolio(String portfolioName);

  /**
   * Display the add portfolio view in the main screen.
   */
  void showAddPortfolio();

  /**
   * Display the load portfolio view in the main screen.
   */
  void showLoadPortfolio();

  /**
   * Display the buy and sell stock view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showBuySellStock(String portfolioName);

  /**
   * Display the portfolio cost basis view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showCostBasis(String portfolioName);

  /**
   * Display the portfolio composition at date view in the main screen given the name of the
   * portfolio and the composition of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   * @param composition   the provided portfolio composition
   */
  void showCompositionAtDate(String portfolioName, List<String> composition);

  /**
   * Display the portfolio strategy view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showAddStrategy(String portfolioName);

  /**
   * Display the portfolio buy stocks with weights view in the main screen given the name of the
   * portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showBuyStocksWithWeights(String portfolioName);


  /**
   * Display the home screen view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showHome(String portfolioName);

  /**
   * Add action listeners with the provided set of features supported by views.
   *
   * @param features the provided the set of features
   */
  void addActionListener(Features features);

  /**
   * Display the performance date input view in the main screen given the name of the portfolio.
   *
   * @param portfolioName the provided portfolio name
   */
  void showInputPerformanceDates(String portfolioName);
}
