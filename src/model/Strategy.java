package model;

import java.util.Map;

/**
 * This represents the strategy for applying to the portfolio.
 */
public interface Strategy {

  /**
   * This helps to get the amount invested by this strategy.
   *
   * @return returns the amount of the portfolio.
   */
  double getAmount();

  /**
   * This helps to get the gap in days for the recurring strategy.
   *
   * @return returns number of days.
   */
  int getIntervalInDays();

  /**
   * This helps to get the start date of the strategy.
   *
   * @return returns the start date as string.
   */
  String getStartDate();

  /**
   * This helps to set the start date of the strategy.
   *
   * @param startDate takes input as start date of strategy.
   */
  void setStartDate(String startDate);

  /**
   * This helps to get the end date of the strategy.
   *
   * @return returns the end date of strategy.
   */
  String getEndDate();

  /**
   * This helpos to get the commission for strategy.
   *
   * @return returns the commission of strategy.
   */
  double getCommission();

  /**
   * This helps to get the weight of stock and its percentage.
   *
   * @return returns the stock and its percentage to invest in portfolio.
   */
  Map<String, Double> getWeights();
}
