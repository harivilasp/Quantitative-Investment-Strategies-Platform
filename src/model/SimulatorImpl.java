package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import utils.Constants;
import utils.Utils;

/**
 * The Model class implementation of simulator of program.
 */
public class SimulatorImpl implements Simulator {

  private Portfolio inflexiblePortfolio;
  private FlexiblePortfolio flexiblePortfolio;

  @Override
  public boolean isPortfolioChosen() {
    return inflexiblePortfolio != null || flexiblePortfolio != null;
  }

  @Override
  public void resetPortfolios() {
    this.inflexiblePortfolio = null;
    this.flexiblePortfolio = null;
  }

  /**
   * Constructs an instance of the Simulator model.
   */
  public SimulatorImpl() throws IllegalArgumentException {
    this.inflexiblePortfolio = null;
    this.flexiblePortfolio = null;

    // Load the valid stock symbols once the model is instantiated.
    Utils.loadValidStocks();
  }

  @Override
  public double getValue(String date) throws IllegalArgumentException, RuntimeException {
    if (inflexiblePortfolio != null) {
      return inflexiblePortfolio.getValue(date);
    }

    return flexiblePortfolio.getValue(date);
  }

  @Override
  public String getName() {
    if (inflexiblePortfolio != null) {
      return inflexiblePortfolio.getName();
    }

    return flexiblePortfolio.getName();
  }

  @Override
  public void save() throws IOException, RuntimeException {
    if (inflexiblePortfolio != null) {
      inflexiblePortfolio.save();
      return;
    }

    flexiblePortfolio.save();
  }

  @Override
  public List<Stock> getComposition() {
    if (inflexiblePortfolio != null) {
      return inflexiblePortfolio.getComposition();
    }

    return flexiblePortfolio.getComposition();
  }

  @Override
  public void addPortfolio(String portfolioName, List<Stock> stocks) {
    flexiblePortfolio = null;
    inflexiblePortfolio = new PortfolioImpl(portfolioName, stocks);
  }

  @Override
  public void addFlexiblePortfolio(String portfolioName) {
    inflexiblePortfolio = null;
    flexiblePortfolio = new FlexiblePortfolioImpl(portfolioName);  //
  }

  @Override
  public void loadPortfolio(String filepath) throws IllegalArgumentException, RuntimeException {
    inflexiblePortfolio = new PortfolioImpl(filepath);
    flexiblePortfolio = null;
  }

  @Override
  public void loadFlexiblePortfolio(String filepath)
          throws IllegalArgumentException, RuntimeException {
    inflexiblePortfolio = null;
    flexiblePortfolio = new FlexiblePortfolioImpl(filepath, Utils.VALID_STOCKS);
  }

  @Override
  public Stock generateStock(String name, int quantity) throws IllegalArgumentException {
    // If the stock name is not part of the publicly listed stock names
    if (name.isEmpty() || !Utils.VALID_STOCKS.contains(name)) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_STOCK_DATA);
    }

    // If the quantity is of an invalid format
    if (quantity <= 0) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_STOCK_DATA);
    }

    return new Stock(name, quantity);
  }

  @Override
  public void buyStock(String stockName, int stockQty, String date, double commission)
          throws RuntimeException {
    Stock stock = this.generateStock(stockName, stockQty);  // #ignored: IAE. No need.
    this.flexiblePortfolio.buyStock(stock, date, commission);
  }

  @Override
  public void sellStock(String stockName, int stockQty, String date, double commission)
          throws RuntimeException {
    Stock stock = this.generateStock(stockName, stockQty);  // #ignored: IAE. No need.
    this.flexiblePortfolio.sellStock(stock, date, commission);
  }

  private Map<String, Integer> calculateValueScale(Map<String, Integer> performances) {
    ArrayList<Integer> vals = new ArrayList<Integer>(performances.values());
    vals.sort((Integer a, Integer b) -> a.compareTo(b));
    double mi = vals.get(0);
    if (mi == 0) {
      for (int val : vals) {
        if (val > 0) {
          mi = val;
          break;
        }
      }
    }
    int ma = vals.get(vals.size() - 1);
    //System.out.println(ma/mi);
    double scalediff = ma - mi;
    scalediff /= 10;
    mi -= scalediff;
    // relative
    for (Map.Entry<String, Integer> entry : performances.entrySet()) {
      String key = entry.getKey();
      if (performances.get(key) > 0) {
        performances.put(key, (int) Math.round((performances.get(key) - mi) / scalediff));
      }
    }
    performances.put("Scale (relative) [$" + Double.toString(mi) + "]",
            (int) Math.round(scalediff));
    return performances;
  }

  private Map<String, Integer> calculateDateScale(Portfolio portfolio
          , Date firstDate, Date secondDate, String endDate) {
    Map<String, Integer> performances = new TreeMap<>();
    Calendar c = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    long diffInMillis = Math.abs(secondDate.getTime() - firstDate.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

    if (diff <= 7) {
      c.setTime(firstDate);
      while (c.getTime().compareTo(secondDate) <= 0) {
        String nesDate = sdf.format(c.getTime());
        performances.put(nesDate, (int) portfolio.getValue(nesDate));
        c.add(Calendar.DATE, 1);
      }
    } else if (diff <= 70) {
      c.setTime(firstDate);
      while (c.getTime().compareTo(secondDate) <= 0) {
        String nesDate = sdf.format(c.getTime());
        performances.put(nesDate, (int) portfolio.getValue(nesDate));
        c.add(Calendar.DATE, 7);
      }
    } else if (diff <= 366) {
      c.setTime(firstDate);
      while (c.getTime().compareTo(secondDate) <= 0) {
        String nextDate = sdf.format(c.getTime());
        performances.put(nextDate, (int) portfolio.getValue(nextDate));
        c.add(Calendar.MONTH, 1);
      }
    } else {
      long jump;
      diff = secondDate.getYear() - firstDate.getYear();
      if (diff > 10) {
        jump = diff / 10;
      } else {
        jump = 1;
      }

      c.setTime(firstDate);
      while (c.getTime().compareTo(secondDate) <= 0) {
        String nesDate = sdf.format(c.getTime());
        performances.put(nesDate, (int) portfolio.getValue(nesDate));
        c.add(Calendar.YEAR, (int) jump);
      }
    }
    performances.put(endDate, (int) portfolio.getValue(endDate));
    return performances;
  }

  @Override
  public Map<String, Integer> getPerformance(String startDate, String endDate)
          throws IllegalArgumentException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Map<String, Integer> performances = new TreeMap<>();
    Calendar c = Calendar.getInstance();

    Portfolio portfolio = (inflexiblePortfolio != null) ? inflexiblePortfolio : flexiblePortfolio;

    Date firstDate;
    try {
      firstDate = sdf.parse(startDate);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    Date secondDate;
    try {
      secondDate = sdf.parse(endDate);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    if (firstDate.compareTo(secondDate) > 0) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }
    performances = calculateDateScale(portfolio, firstDate, secondDate, endDate);
    performances = calculateValueScale(performances);

    //System.out.println(performances);
    return performances;
  }

  public double getCostBasis(String date) throws Exception {
    return flexiblePortfolio.getCostBasis(date);
  }

  public List<Stock> getCompositionAtDate(String date) throws RuntimeException {
    return flexiblePortfolio.getCompositionAtDate(date);
  }

  @Override
  public void addStrategy(double amount, int intervalInDays,
                          String startDate, String endDate, double commission,
                          Map<String, Double> weights) throws Exception {
    if (inflexiblePortfolio != null) {
      throw new Exception("Operation only supported on Inflexible Portfolio");
    }
    flexiblePortfolio.addStrategy(amount, intervalInDays, startDate, endDate, commission, weights);
  }

  @Override
  public void buyStocksWithWeights(double amount, String date, double commission,
                                   Map<String, Double> weights) throws Exception {
    if (inflexiblePortfolio != null) {
      throw new Exception("Operation only supported on Inflexible Portfolio");
    }
    flexiblePortfolio.buyStocksWithWeights(amount, date, commission, weights);
  }

}
