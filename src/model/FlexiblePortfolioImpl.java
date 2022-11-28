package model;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import utils.Constants;

/**
 * This class represents flexible portfolio which is mutable i.e. it is possible to add and delete
 * stocks after creation.
 */
public class FlexiblePortfolioImpl implements FlexiblePortfolio {

  private final Portfolio delegate;
  private final List<Transaction> transactions;
  private FileIO fileIO;
  private List<Strategy> strategies;

  /**
   * This constructs object of portfolio with portfolio name.
   *
   * @param portfolioName represents the name of the portfolio.
   */
  public FlexiblePortfolioImpl(String portfolioName) {
    fileIO = new FileIOSCS();
    List<Stock> stocks = new ArrayList<>();
    this.delegate = new PortfolioImpl(portfolioName, stocks);
    transactions = new ArrayList<>();
    strategies = new ArrayList<>();
  }

  /**
   * This constructs the portfolio from filepath and valid stocks.
   *
   * @param filePath    represents path of file.
   * @param validStocks represents the supported stocks.
   * @throws IllegalArgumentException when invalid data in file.
   * @throws RuntimeException         when error occurs while reading.
   */
  public FlexiblePortfolioImpl(String filePath, Set<String> validStocks)
          throws IllegalArgumentException, RuntimeException {
    fileIO = new FileIOSCS();
    FlexiblePortfolioImpl flexiblePortfolio =
            (FlexiblePortfolioImpl) fileIO.readFlexiblePortfolio(filePath);
    this.transactions = flexiblePortfolio.transactions;
    this.delegate = flexiblePortfolio.delegate;
    this.strategies = new ArrayList<>();
  }

  /**
   * This constructs the portfolio with transactions and portfolio.
   *
   * @param transactions represents the list of transactions.
   * @param delegate     represents the portfolio type object.
   */
  FlexiblePortfolioImpl(List<Transaction> transactions, Portfolio delegate) {
    this.delegate = delegate;
    this.transactions = transactions;
    this.strategies = new ArrayList<>();
  }

  @Override
  public void buyStock(Stock stock, String date, double commission) throws RuntimeException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    try {
      sdf.parse(date);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }
    List<Stock> stocks = delegate.getComposition();
    boolean isAdded = false;

    for (Stock presentStock : stocks) {
      if (stock.getName().equals(presentStock.getName())) {
        // Change composition (quantity) of existing stock.
        presentStock.setQuantity(presentStock.getQuantity() + stock.getQuantity());
        isAdded = true;
        break;
      }
    }

    if (!isAdded) {
      stocks.add(stock);
    }

    transactions.add(new Transaction(stock.getName(), stock.getQuantity(), date, commission));
  }

  @Override
  public void save() throws IOException, RuntimeException {
    fileIO.writeFlexiblePortfolio(this.getName(), transactions, strategies);
  }

  @Override
  public void sellStock(Stock stock, String date, double commission) throws RuntimeException {
    List<Stock> stocks = delegate.getComposition();
    boolean isSold = false;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    try {
      sdf.parse(date);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    List<Stock> compositionAtDate = this.getCompositionAtDate(date);
    // List to Map
    Map<String, Double> compDateMap = new HashMap<>();
    for (Stock stock1 : compositionAtDate) {
      compDateMap.put(stock1.getName(), stock1.getQuantity());
    }

    for (Stock presentStock : stocks) {
      if (stock.getName().equals(presentStock.getName())) {
        if (compDateMap.get(stock.getName()) >= stock.getQuantity()) {
          // Change composition (quantity) of existing stock.
          presentStock.setQuantity(presentStock.getQuantity() - stock.getQuantity());
          isSold = true;
          break;
        } else {
          throw new RuntimeException(Constants.ERR_INS_STOCK_DATA);
        }
      }
    }

    if (!isSold) {
      throw new RuntimeException(Constants.ERR_STOCK_NF);
    }

    transactions.add(new Transaction(stock.getName(), -(stock.getQuantity()), date, commission));
  }

  @Override
  public List<Stock> getComposition() {
    return delegate.getComposition();
  }

  @Override
  public double getValue(String date) throws IllegalArgumentException, RuntimeException {
    // Format the date parameter.
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    // Parse the date
    Date d1;
    try {
      d1 = format.parse(date);
    } catch (ParseException exception) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    // Current date (local time)
    Date d2 = new Date();

    // If provided date is in the future (invalid).
    if (d1.compareTo(d2) >= 0) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }

    double value = 0;
    List<Stock> composition = getCompositionAtDate(date);
    for (Stock stock : composition) {
      try {
        value += (stock.getQuantity() * AlphaVantgeApiUtil.getInstance()
                .getValue(stock.getName(), date));
      } catch (RuntimeException re) {
        throw new RuntimeException(Constants.ERR_API_TIMEOUT);
      }
    }
    return value;
  }

  @Override
  public String getName() {
    return delegate.getName();
  }

  @Override
  public double getCostBasis(String date) throws Exception {
    // Format the date parameter.
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    format.setLenient(false);

    // Parse the date
    Date formattedDate;
    try {
      formattedDate = format.parse(date);
    } catch (ParseException exception) {
      throw new RuntimeException(Constants.ERR_INVALID_DATE);
    }
    Date todayDate = new Date();
    double cost = 0;
    if (formattedDate.compareTo(todayDate) >= 0) {
      double tempcost = processStrategy(date);
      System.out.println(tempcost + " " + cost);
      cost += tempcost;
    }
    // Assuming transactions are sorted.
    for (Transaction transaction : transactions) {
      if (date.compareTo(transaction.getDate()) >= 0) {
        cost += transaction.getCommission();

        double qty = transaction.getQuantity();
        if (qty > 0) {
          try {
            cost += qty * AlphaVantgeApiUtil.getInstance()
                    .getValue(transaction.getStockName(), transaction.getDate());
          } catch (RuntimeException re) {
            throw new IllegalArgumentException(Constants.ERR_API_TIMEOUT);
          }
        }
      }
    }

    return cost;
  }

  private double processStrategy(String date) throws Exception {
    double tempcost = 0;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Date todayDate = new Date();
    Date secondDate;
    try {
      secondDate = sdf.parse(date);
    } catch (ParseException pe) {
      throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
    }
    for (Strategy strategy : strategies) {
      if (sdf.format(todayDate).compareTo(strategy.getStartDate()) >= 0) {
        Calendar c = Calendar.getInstance();
        Date firstDate;
        try {
          firstDate = sdf.parse(strategy.getStartDate());
        } catch (ParseException pe) {
          throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
        }

        c.setTime(firstDate);
        while (c.getTime().compareTo(todayDate) < 0) {
          String nextDate = sdf.format(c.getTime());
          this.buyStocksWithWeights(strategy.getAmount(), nextDate,
                  strategy.getCommission(), strategy.getWeights());
          c.add(Calendar.DATE, strategy.getIntervalInDays());
        }
        if (strategy.getEndDate().compareTo(sdf.format(todayDate)) < 0) {
          strategies.remove(strategy);
        } else {
          strategy.setStartDate(sdf.format(todayDate));
          long diffInMillis = Math.abs(secondDate.getTime() - c.getTime().getTime());
          long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
          System.out.println(diff + " diff");
          tempcost += (diff / strategy.getIntervalInDays()) * (strategy.getCommission()
                  + strategy.getAmount());
        }
      }
      if (sdf.format(secondDate).compareTo(strategy.getStartDate()) >= 0) {
        long diffInMillis;
        long diff;
        if (sdf.format(secondDate).compareTo(strategy.getEndDate()) > 0) {
          diffInMillis = Math.abs(
                  sdf.parse(strategy.getEndDate()).getTime() - sdf.parse(strategy.getStartDate())
                          .getTime());
        } else {
          diffInMillis = Math.abs(
                  secondDate.getTime() - sdf.parse(strategy.getStartDate()).getTime());
        }
        diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
        System.out.println(diff + " diff");
        tempcost += (diff / strategy.getIntervalInDays()) * (strategy.getCommission()
                + strategy.getAmount());
      }
    }
    return tempcost;
  }

  @Override
  public List<Stock> getCompositionAtDate(String date) {
    Map<String, Double> stocks = new TreeMap<>();
    for (Transaction transaction : transactions) {
      if (date.compareTo(transaction.getDate()) >= 0) {
        stocks.put(
                transaction.getStockName(),
                transaction.getQuantity()
                        + stocks.getOrDefault(transaction.getStockName(), 0.0)
        );
      }
    }

    // Convert map to list
    List<Stock> stockList = new ArrayList<>();
    for (Map.Entry<String, Double> entry : stocks.entrySet()) {
      stockList.add(
              new Stock(entry.getKey(), entry.getValue())
      );
    }

    return stockList;
  }

  @Override
  public void addStrategy(double amount, int intervalInDays,
                          String startDate, String endDate, double commission,
                          Map<String, Double> weights) throws Exception {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    Calendar c = Calendar.getInstance();
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
    c.setTime(firstDate);
    Date todayDate = new Date();
    if (todayDate.compareTo(secondDate) > 0) {
      todayDate = secondDate;
    }
    while (c.getTime().compareTo(todayDate) < 0) {
      String nextDate = sdf.format(c.getTime());
      this.buyStocksWithWeights(amount, nextDate, commission, weights);
      c.add(Calendar.DATE, intervalInDays);
    }

    if (c.getTime().compareTo(secondDate) < 0) {
      Strategy strategy = new DollarCostAveragingStrategy(amount,
              intervalInDays, sdf.format(c.getTime()), endDate, commission, weights);
      strategies.add(strategy);
    }
  }

  @Override
  public void buyStocksWithWeights(double amount, String date,
                                   double commission, Map<String, Double> weights)
          throws Exception {
    amount = amount - commission;
    // future date
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    try {
      Date buyDate = sdf.parse(date);
      Date todayDate = new Date();
      if (todayDate.compareTo(buyDate) <= 0) {
        throw new IllegalArgumentException(Constants.ERR_INVALID_DATE);
      }
      double totalWeight = 0;
      for (double weight : weights.values()) {
        totalWeight += weight;
      }
      if (Math.abs(100.00 - totalWeight) > 0.01) {
        throw new IllegalArgumentException(Constants.ERR_INVALID_STOCK_DATA);
      }
    } catch (Exception pe) {
      throw pe;
    }
    for (Map.Entry<String, Double> entry : weights.entrySet()) {
      String stockName = entry.getKey();
      double valueAtDate = AlphaVantgeApiUtil.getInstance().getValue(stockName, date);
      double quantity = (amount * entry.getValue() / 100.0) / valueAtDate;
      this.buyStock(new Stock(stockName, quantity), date, commission);
    }
  }
}
