import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Simulator;
import model.SimulatorImpl;
import model.Stock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class tests simulator methods when selected portfolio is flexible portfolio.
 */
public class SimulatorFlexiblePortfolioTest {

  Simulator simulator;

  @Before
  public void setUp() {
    simulator = new SimulatorImpl();
    simulator.addFlexiblePortfolio("testporfolio");
    simulator.buyStock("GOOG", 3, "2022-08-10", 0.2);
    simulator.buyStock("GOOG", 3, "2022-09-10", 0.2);
    simulator.buyStock("GOOG", 3, "2022-10-10", 0.2);
    simulator.buyStock("GOOG", 3, "2022-11-01", 0.2);
  }

  @Test(expected = RuntimeException.class)
  public void testSellStockInSufficientStock() throws IOException {
    simulator.sellStock("GOOG", 6, "2022-08-20", 0.2);
    String s = "Error";
    assertEquals("Error", s);
  }

  @Test(expected = Exception.class)
  public void testSellStockInvalidDate() throws IOException {
    simulator.sellStock("GOOG", 6, "2021-08-90", 0.2);
    String s = "Error";
    assertEquals("Error", s);
  }

  @Test(expected = Exception.class)
  public void testBuyStockInvalidDate() throws IOException {
    simulator.buyStock("GOOG", 6, "2014-20-22", 0.2);
    String s = "Error";
    assertEquals("Error", s);
  }

  @Test()
  public void testSellStockWhenSufficientStock() throws IOException {
    simulator.sellStock("GOOG", 1, "2022-08-20", 0.2);
    assertEquals("[GOOG=11.0]", simulator.getComposition().toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSellStockWithNegativeCommission() throws IOException {
    simulator.sellStock("GOOG", 1, "2022-08-20", -0.2);
    assertEquals("[GOOG=11.0]", simulator.getComposition().toString());
  }

  @Test
  public void testLoadFlexibleStock() throws IOException {
    simulator.loadFlexiblePortfolio("flexibleportfolios/TestFlexible.txt");
    simulator.save();
    assertTrue(simulator.isPortfolioChosen());
  }

  @Test
  public void testGetValue() throws RuntimeException, ParseException {
    double value = simulator.getValue("2022-10-15");
    assertEquals(874.62, value, 0.02);
  }

  @Test
  public void testGetValueWhenNoStock() throws RuntimeException, ParseException {
    double value = simulator.getValue("2015-10-15");
    System.out.println(value);
    assertEquals(0.0, value, 0.01);
  }

  @Test
  public void testGetPerformanceYears() throws RuntimeException, ParseException {
    Map<String, Integer> performances = simulator.getPerformance("2015-09-19", "2022-10-15");
    assertEquals("{2015-09-19=0, 2016-09-19=0, 2017-09-19=0, 2018-09-19=0, "
            + "2019-09-19=0, 2020-09-19=0, 2021-09-19=0, 2022-09-19=1, 2022-10-15=11, "
            + "Scale (relative) [$597.9]=25}", performances.toString());
  }

  @Test
  public void testGetPerformanceOverMonths() throws RuntimeException, ParseException {
    Map<String, Integer> performances = simulator.getPerformance("2022-08-19", "2022-10-15");

  }


  @Test
  public void testGetPerformanceYearsLargediff() throws RuntimeException, ParseException {
    Map<String, Integer> performances = simulator.getPerformance("1950-09-19", "2022-10-15");
    assertEquals("{1950-09-19=0, 1957-09-19=0, 1964-09-19=0, 1971-09-19=0, "
                    + "1978-09-19=0, 1985-09-19=0, 1992-09-19=0, 1999-09-19=0, "
                    + "2006-09-19=0, 2013-09-19=0, 2020-09-19=0, 2022-10-15=0, "
                    + "Scale (relative) [$874.0]=0}",
            performances.toString());
  }

  @Test
  public void testGetCostBasis() throws RuntimeException, Exception {
    double costBasis = simulator.getCostBasis("2022-08-30");
    simulator.sellStock("GOOG", 1, "2022-08-20", 0.2);
    assertEquals(362.15, costBasis, 0.01);
  }

  @Test
  public void testPortfolioCompositionAtDate() throws ParseException {
    List<Stock> expected = new ArrayList<Stock>();
    expected.add(new Stock("GOOG", 6));
    assertEquals(expected.toString(),
            simulator.getCompositionAtDate("2022-09-10").toString());
  }

  @Test
  public void testSaveFlexiblePortfolio() throws IOException {
    simulator.sellStock("GOOG", 3, "2022-08-10", 0.2);
    simulator.save();
    assertTrue(simulator.isPortfolioChosen());
  }

  @Test
  public void testLoadFlexiblePortfolio() throws Exception {
    simulator.loadFlexiblePortfolio("flexibleportfolios/testLoadPortfolio.txt");
    assertTrue(simulator.isPortfolioChosen());
    assertEquals("[NFLX=24.0, MSFT=17.0, AAPL=21.0]", simulator.getComposition().toString());
  }


  @Test
  public void test() throws IOException {
    simulator.loadFlexiblePortfolio("flexibleportfolios/testLoadPortfolio.txt");
    assertTrue(simulator.isPortfolioChosen());
    assertEquals("[NFLX=24.0, MSFT=24.0, AAPL=21.0]", simulator.getComposition().toString());
  }

  @Test(expected = Exception.class)
  public void testBuyStocksWithWeightsWithInvalidDate() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.buyStocksWithWeights(2000.0, "2-09-09", 0.4, weights);
  }

  @Test
  public void testBuyStocksWithWeights() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.buyStocksWithWeights(2000.0, "2022-09-09", 0.4, weights);
    System.out.println(simulator.getCompositionAtDate("2022-09-08").toString());
    System.out.println(simulator.getCompositionAtDate("2022-09-18").toString());
  }

  @Test
  public void testAddStrategy() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0
            , 30, "2019-01-01", "2021-12-31", 0.4, weights);
    System.out.println(simulator.getCompositionAtDate("2019-09-01").toString());
    System.out.println(simulator.getCompositionAtDate("2019-10-01").toString());
    System.out.println(simulator.getCompositionAtDate("2019-11-02").toString());
    System.out.println(simulator.getCompositionAtDate("2019-12-02").toString());
  }

  @Test
  public void testValueAfterAddStrategy() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0, 15, "2019-01-01", "2021-12-31", 0.4, weights);

    assertEquals(33961.59, simulator.getValue("2019-09-03"), 0.01);
    assertEquals(104303.69, simulator.getValue("2020-11-01"), 0.01);
    assertEquals(127096.41, simulator.getValue("2021-02-02"), 0.01);
  }

  @Test(expected = Exception.class)
  public void testAddStrategyWithInvalidDate() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0, 30, "201-01", "2015-12-31", 0.4, weights);
  }

  @Test(expected = Exception.class)
  public void testAddStrategyWithSmallerEndDate() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0, 30, "2015-01-01", "2012-12-31", 0.4, weights);
  }

  @Test
  public void testAddStrategyWithFutureEndDate() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0
            , 30, "2022-01-01", "2024-12-31", 0.4, weights);
    System.out.println(simulator.getCompositionAtDate("2019-09-01").toString());
    System.out.println(simulator.getCompositionAtDate("2022-10-01").toString());
    System.out.println(simulator.getCompositionAtDate("2022-11-02").toString());
  }

  @Test
  public void testAddStrategyWithNoEndDate() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0
            , 30, "2012-01-01", "2095-12-31", 0.4, weights);
  }

  @Test
  public void testCostBasisAfterAtFutureDateAfterFutureStrategy() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("META", 40.0);
    weights.put("AAPL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("GOOG", 10.0);
    simulator.addStrategy(2000.0, 30
            , "2021-11-21", "2023-12-01", 0.4, weights);
    assertEquals(2001.20, simulator.getCostBasis("2021-12-01"), 0.01);
    assertEquals(22710.89, simulator.getCostBasis("2022-10-01"), 0.01);
    assertEquals(45287.50, simulator.getCostBasis("2023-10-01"), 0.01);
    assertEquals(49288.30, simulator.getCostBasis("2024-10-01"), 0.01);
  }

  @Test
  public void testCostBasisAfterStrategy() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("MSFT", 40.0);
    weights.put("AAL", 20.0);
    weights.put("NFLX", 30.0);
    weights.put("AAPL", 10.0);
    simulator.addStrategy(2000.0
            , 30, "2021-11-21", "2022-11-30", 0.4, weights);
    assertEquals(2001.20, simulator.getCostBasis("2021-12-01"), 0.01);
    assertEquals(22710.89, simulator.getCostBasis("2022-10-01"), 0.01);
    assertEquals(27283.90, simulator.getCostBasis("2023-10-01"), 0.01);
  }

  @Test
  public void testAddStrategyDaily() throws Exception {
    Map<String, Double> weights = new HashMap<>();
    weights.put("MSFT", 25.0);
    weights.put("GOOG", 50.0);
    weights.put("NFLX", 15.0);
    weights.put("AAPL", 10.0);
    simulator.addStrategy(2000.0
            , 7, "2021-02-11", "2021-02-30", 0.0, weights);
  }

}
