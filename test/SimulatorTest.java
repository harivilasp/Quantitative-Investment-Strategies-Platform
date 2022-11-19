import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.Simulator;
import model.SimulatorImpl;
import model.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This class tests the simulator model class.
 */
public class SimulatorTest {

  @Test
  public void testLoadPortfolio() {
    Simulator simulator = new SimulatorImpl();
    try {
      simulator.loadPortfolio("portfolios/Tech Portfolio.txt");
    } catch (RuntimeException re) {
      fail("Test failed: Portfolio loading unsuccessful!");
    }
  }

  @Test
  public void testValuePortfolio() {
    Simulator simulator = new SimulatorImpl();
    try {
      simulator.loadPortfolio("portfolios/Tech Portfolio.txt");
    } catch (RuntimeException re) {
      fail("Test failed: Portfolio loading unsuccessful!");
    }
    assertEquals(16965.69, simulator.getValue("2022-08-02"), 0.01);
  }

  @Test
  public void testAddPortfolio() {
    Simulator simulator = new SimulatorImpl();
    List<Stock> stocks = new ArrayList<>();
    stocks.add(new Stock("GOOG", 1));
    simulator.addPortfolio("Personal", stocks);
    assertEquals("[GOOG=1.0]", simulator.getComposition().toString());
  }

  @Test
  public void testAddPortfolio2Stocks() {
    Simulator simulator = new SimulatorImpl();
    List<Stock> stocks = new ArrayList<>();
    stocks.add(new Stock("AAPL", 1));
    stocks.add(new Stock("GOOG", 1));
    simulator.addPortfolio("Personal", stocks);

    assertEquals("[AAPL=1.0, GOOG=1.0]", simulator.getComposition().toString());
  }

  @Test
  public void testGenerateStock() {
    Simulator simulator = new SimulatorImpl();
    Stock stock = simulator.generateStock("AAPL", 1);
    assertEquals("AAPL=1.0", stock.toString());
  }
}
