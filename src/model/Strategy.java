package model;

import java.util.Map;

public interface Strategy {

  double getAmount();

  int getIntervalInDays();

  String getStartDate();

  public void setStartDate(String startDate);

  String getEndDate();

  double getCommission();

  Map<String, Double> getWeights();
}
