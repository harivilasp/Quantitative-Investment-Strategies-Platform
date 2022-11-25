package controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface Features {

  /**
   * Provides information about whether a portfolio has been selected by the user.
   *
   * @return whether a portfolio has been selected
   */
  boolean isPortfolioChosen();

  /**
   * Helps to reset the selection of portfolios in the model.
   */
  void resetPortfolios();

  /**
   * This helps to get the current value of portfolio at certain date.
   *
   * @param date represents the date as argument for getting value.
   * @return the value of all stocks with current value and quantity.
   * @throws IllegalArgumentException if invalid date is passed.
   * @throws RuntimeException         if errors like API timeout, or other error.
   */
  double getValue(String date) throws IllegalArgumentException, RuntimeException;

  /**
   * This helps to get the name of the portfolio set by the user.
   *
   * @return the portfolio name
   */
  String getName();

  /**
   * This helps to make in memory portfolio persistent by saving it as a text file.
   *
   * @throws IOException      if error occurs while saving file
   * @throws RuntimeException if improper data
   */
  String save() throws IOException, RuntimeException;

  /**
   * This method helps to create new empty flexible portfolio.
   *
   * @param portfolioName the portfolio name
   */
  String addFlexiblePortfolio(String portfolioName);

  /**
   * This method helps to load flexible portfolio form existing file.
   *
   * @param filepath the relative file path
   * @throws IllegalArgumentException in case of invalid filepath
   * @throws RuntimeException         if the portfolio does not exist or is of an invalid format
   */
  String loadFlexiblePortfolio(String filepath) throws IllegalArgumentException, RuntimeException;


  String buyStock(String stockName, int stockQty, String date, double commission);

  String sellStock(String stockName, int stockQty, String date, double commission);

  String getCostBasis(String date);

  List<String> getCompositionAtDate(String date);

  String addStrategy(double amount, int intervalInDays,
                     String startDate, String endDate, double commission,
                     Map<String, Double> weights);

  String buyStocksWithWeights(double amount, String date, double commission,
                              Map<String,Double> weights);

  /**
   * This method calculated the map to draws the graph of performance in between two dates.
   *
   * @param startDate represents the start date.
   * @param endDate   represents the end date.
   * @return returns the map of date and number of stars to print.
   * @throws IllegalArgumentException when some invalid date is passed.
   */
  Map<String, Integer> getPerformance(String startDate, String endDate);

  void exitProgram();
}
