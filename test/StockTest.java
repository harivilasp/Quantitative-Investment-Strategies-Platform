import org.junit.Test;

import model.Stock;

import static org.junit.Assert.assertEquals;

/**
 * This class tests stock model.
 */
public class StockTest {

  @Test
  public void testStockCreationByNameAndStock() {
    Stock stock = new Stock("GOOG", 4);
    assertEquals("GOOG=4.0", stock.toString());
  }

  @Test
  public void testStockCreationByName() {
    Stock stock = new Stock("GOOG", 4);
    assertEquals("GOOG", stock.getName());
  }

  @Test
  public void testStockCreationStock() {
    Stock stock = new Stock("GOOG", 4);
    assertEquals("4", Integer.toString((int) stock.getQuantity()));
  }

  @Test
  public void testGetPriceAtDate() {
    Stock stock = new Stock("GOOG", 4);
    assertEquals("100.74", Double.toString(stock.getPriceAtDate("2022-09-29")));
  }
}
