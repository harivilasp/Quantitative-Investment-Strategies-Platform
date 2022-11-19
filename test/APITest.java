import org.junit.Test;

import model.APIUtil;
import model.AlphaVantgeApiUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class tests API calling and cache testing.
 */
public class APITest {

  APIUtil apiUtil;

  @Test
  public void testValueFetch() throws RuntimeException {
    double val = AlphaVantgeApiUtil.getInstance().getValue("GOOG", "2022-05-04");
    assertEquals(3223, val, 0.01);
  }

  @Test
  public void testCache() throws RuntimeException {
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2021-10-03"), 0.01);
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2021-11-03"), 0.01);
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2022-10-03"), 0.01);
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2022-11-03"), 0.01);
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2022-11-13"), 0.01);
  }

  @Test(expected = RuntimeException.class)
  public void testFutureDate() throws RuntimeException {
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2024-08-28"), 0.01);
  }

  @Test(expected = RuntimeException.class)
  public void testVeryOldDate() throws RuntimeException {
    assertEquals(3223, AlphaVantgeApiUtil.getInstance()
            .getValue("GOOG", "2001-12-28"), 0.01);
  }

  private void binarySearch() {
    String prices = "2022-12-01.3\n2022-11-05.43\n2022-11-04.23\n2022-11-03.3\n2022-10-01.3";
    String[] rows = prices.split("\n");
    String date = "2022-09-28";
    int low = -1;
    int high = rows.length;
    while (low <= high) {
      int mid = (low + high) / 2;
      if (mid < 0 || mid >= rows.length) {
        break;
      }
      System.out.println(date + "->" + rows[mid]);
      if (date.compareTo(rows[mid]) <= 0) {
        low = mid + 1;
      } else {
        high = mid - 1;
      }
    }
    if (low > 0 && low < rows.length) {
      System.out.println(rows[low]);
    } else {
      System.out.println("NOt found");
    }
  }
}
