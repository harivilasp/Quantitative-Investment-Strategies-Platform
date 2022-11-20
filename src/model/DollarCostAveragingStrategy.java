package model;

import java.util.Map;

public class DollarCostAveragingStrategy implements Strategy{

  double amount;
  int intervalInDays;
  String startDate;
  String endDate;
  double commission;
  Map<String,Double> weights;



  public DollarCostAveragingStrategy(double amount, int intervalInDays,
                                     String startDate, String endDate, double commission,
                                     Map<String, Double> weights) {
    this.amount = amount;
    this.intervalInDays = intervalInDays;
    this.startDate = startDate;
    this.endDate = endDate;
    this.weights = weights;
    this.commission = commission;
  }

  public double getAmount() {
    return amount;
  }

  public int getIntervalInDays() {
    return intervalInDays;
  }

  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate=startDate;
  }

  public String getEndDate() {
    return endDate;
  }

  public double getCommission() {
    return commission;
  }

  public Map<String, Double> getWeights() {
    return weights;
  }
}
