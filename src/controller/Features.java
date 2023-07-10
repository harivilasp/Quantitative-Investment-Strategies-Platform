package controller;

import java.util.List;
import java.util.Map;

/**
 * This interface represents all the features provided by the controller and supported by multiple
 * views. Removes coupling between controller and the views.
 */
public interface Features {

  /**
   * Provides information about whether a portfolio has been selected by the user.
   *
   * @return whether a portfolio has been selected
   */
  boolean isPortfolioChosen();

  /**
   * This helps to get the current value of portfolio at certain date.
   *
   * @param date represents the date as argument for getting value.
   * @return the value of all stocks with current value and quantity.
   * @throws IllegalArgumentException if invalid date is passed.
   * @throws RuntimeException         if errors like API timeout, or other error.
   */
  String getValue(String date) throws IllegalArgumentException, RuntimeException;

  /**
   * This helps to get the name of the portfolio set by the user.
   *
   * @return the portfolio name
   */
  String getName();

  /**
   * This helps to make in memory portfolio persistent by saving it as a text file.
   * @return status of action performed.
   */
  String save();

  /**
   * This method helps to create new empty flexible portfolio.
   *
   * @param portfolioName the portfolio name.
   * @return status of action performed.
   */
  String addFlexiblePortfolio(String portfolioName);

  /**
   * This method helps to load flexible portfolio form existing file.
   *
   * @param filepath the relative file path
   * @return status of action performed.
   */
  String loadFlexiblePortfolio(String filepath);

  /**
   * This method helps to purchase a stock for a portfolio.
   *
   * @param stockName  the name of stock to purchase
   * @param stockQty   the quantity of stock to purchase
   * @param date       the purchase date of the stock
   * @param commission the commission associated with the purchase
   * @return the response status of the operation
   */
  String buyStock(String stockName, int stockQty, String date, double commission);

  /**
   * This method helps to sell a stock for a portfolio.
   *
   * @param stockName  the name of stock to sell
   * @param stockQty   the quantity of stock to sell
   * @param date       the sale date of the stock
   * @param commission the commission associated with the sale
   * @return the response status of the operation
   */
  String sellStock(String stockName, int stockQty, String date, double commission);

  /**
   * This method helps to calculate the cost basis of a portfolio on a date.
   *
   * @param date the provided date
   * @return the calculated cost-basis
   */
  String getCostBasis(String date);

  /**
   * This method helps extract the composition of a portfolio at a particular date.
   *
   * @param date the provided date
   * @return the composition of the portfolio
   */
  List<String> getCompositionAtDate(String date);

  /**
   * This method helps add strategy to a portfolio for buying and selling of stocks.
   *
   * @param amount         the provided balance to use for purchase and sale of stocks
   * @param intervalInDays the interval in days to complete these operations
   * @param startDate      the start date of these operations
   * @param endDate        the end date of these operations (optional)
   * @param commission     the commission associated with these operations
   * @param weights        the stocks weights associated with these operations
   * @return the response status of the operation
   */
  String addStrategy(double amount, int intervalInDays, String startDate, String endDate,
      double commission, Map<String, Double> weights
  );

  /**
   * This method helps buy stocks associated with weights for each stocks for a defined amount.
   *
   * @param amount     the provided amount
   * @param date       the date of the purchase
   * @param commission the commission associated with the purchase
   * @param weights    the defined weights for the stocks
   * @return the response status of the operation
   */
  String buyStocksWithWeights(double amount, String date, double commission,
      Map<String, Double> weights);

  /**
   * This method calculated the map to draws the graph of performance in between two dates.
   *
   * @param startDate represents the start date.
   * @param endDate   represents the end date.
   * @return returns the map of date and number of stars to print.
   * @throws IllegalArgumentException when some invalid date is passed.
   */
  Map<String, Integer> getPerformance(String startDate, String endDate);

  /**
   * This method helps to exit the program.
   */
  void exitProgram();

  /**
   * This method helps to show the home screen.
   */
  void showHome();

  /**
   * This method helps to show the create portfolio screen.
   */
  void showCreatePortfolio();

  /**
   * This method helps to show the portfolio composition screen.
   */
  void showPortfolioComposition();

  /**
   * This method helps to show the buy and sell stock screen.
   */
  void showBuySellStock();

  /**
   * This method helps to show the portfolio cost basis screen.
   */
  void showCostBasis();

  /**
   * This method helps to show the portfolio value at date screen.
   */
  void showPortfolioValue();

  /**
   * This method helps to show the portfolio saving screen.
   */
  void showSavePortfolio();

  /**
   * This method helps to show the portfolio loading screen.
   */
  void showReadPortfolio();

  /**
   * This method helps to show the portfolio strategy creation screen.
   */
  void showCreateStrategy();

  void showInputPerformanceDates();

  void showInvestAmount();

}
