package model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This Singleton class helps to fetch data from web and provides results of the stock value for a
 * particular date.
 */
public class AlphaVantgeApiUtil implements APIUtil {

  private Map<String, List<String>> cache;
  private static AlphaVantgeApiUtil single_instance = null;

  /* Private constructor to allow instance creation control. */
  private AlphaVantgeApiUtil() {
    cache = new HashMap<>();
  }

  /**
   * This is factory method which only creates object when object is not present.
   *
   * @return
   */
  public static AlphaVantgeApiUtil getInstance() {
    if (single_instance == null) {
      single_instance = new AlphaVantgeApiUtil();
    }

    return single_instance;
  }

  @Override
  public double getValue(String symbol, String date) throws RuntimeException {
    date += ",99";
    if (!cache.containsKey(symbol)) {
      String apiKey = "K8KHJR1NUZNEMFX1";
      String stockSymbol = symbol;
      String outputSize = "full";

      URL url = null;
      try {
        url = new URL("https://www.alphavantage"
            + ".co/query?function=TIME_SERIES_DAILY"
            + "&outputsize="
            + outputSize
            + "&symbol"
            + "=" + stockSymbol + "&apikey=" + apiKey + "&datatype=csv");
      } catch (MalformedURLException e) {
        throw new RuntimeException("the alphavantage API has either changed or "
            + "no longer works");
      }

      InputStream in = null;
      StringBuilder output = new StringBuilder();

      try {
        in = url.openStream();
        int b;

        while ((b = in.read()) != -1) {
          output.append((char) b);
        }
      } catch (IOException e) {
        throw new IllegalArgumentException("No price data found for " + stockSymbol);
      }
      List<String> rows = Arrays.asList(output.toString().split("\n"));
      cache.put(symbol, rows);
    }
    List<String> prices = cache.get(symbol);
    // Perform binary search on the results.
    int low = 0;
    int high = prices.size();
    String key = date;
    int index = -1;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (mid <= 0 || mid >= prices.size()) {
        break;
      }
      if (key.compareTo(prices.get(mid)) <= 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (low <= 0 || low >= prices.size()) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String todayDate = sdf.format(new Date());
      if (todayDate.compareTo(key) > 0 && prices.get(1).compareTo(key) <= 0) {
        String[] row = prices.get(1).split(",");
        return Double.parseDouble(row[4]);
      }
      throw new RuntimeException("Not Found or Problem with API");
    }

    if (prices.get(low).substring(0, 10) == key) {
      low = low - 1;
    }

    String[] row = prices.get(low).split(",");
    return Double.parseDouble(row[4]);
  }
}
