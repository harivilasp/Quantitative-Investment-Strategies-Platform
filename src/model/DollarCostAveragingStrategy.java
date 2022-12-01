package model;

import java.util.Map;

/**
 * This class represents the cost averaging strategy implementation of the {@code Strategy}
 * interface.
 */
public class DollarCostAveragingStrategy implements Strategy {

  private double amount;
  private int intervalInDays;
  private String startDate;
  private String endDate;
  private double commission;
  private Map<String, Double> weights;

  /**
   * Constructs an instance of the dollar cost averaging strategy.
   *
   * @param amount         the amount invested in the strategy
   * @param intervalInDays the defined intervals to complete the buy/sell operations on
   * @param startDate      the start date of the strategy
   * @param endDate        the end date of the strategy
   * @param commission     the commission associated with these operations
   * @param weights        the stocks weights associated with these operations
   */
  public DollarCostAveragingStrategy(double amount, int intervalInDays, String startDate,
                                     String endDate, double commission, Map<String, Double> weights) {
    this.amount = amount;
    this.intervalInDays = intervalInDays;
    this.startDate = startDate;
    this.endDate = endDate;
    this.weights = weights;
    this.commission = commission;
  }

  /**
   * Getters
   */
  public double getAmount() {
    return amount;
  }

  public int getIntervalInDays() {
    return intervalInDays;
  }

  public String getStartDate() {
    return startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public double getCommission() {
    return commission;
  }

  /**
   * Setters
   */
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public Map<String, Double> getWeights() {
    return weights;
  }
}
