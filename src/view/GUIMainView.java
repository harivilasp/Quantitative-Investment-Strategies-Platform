package view;

import controller.Features;
import java.awt.Dimension;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is the implementation of the {@code GUIView} interface and completes the transactions
 * of showing multiple views on the screen.
 */
public class GUIMainView extends JFrame implements GUIView {

  private JValueAtDateView portfolioValue;
  private JCreatePortfolioView createPortfolio;
  private JSavePortfolioView savePortfolio;
  private JLoadPortfolioView loadPortfolio;
  private JStrategyView addStrategy;
  private JPanel buyStocksWithWeights;
  private JCostBasisView costBasis;
  private JCompositionView compositionAtDate;
  private JHome home;
  private JPanel currentPanel;
  private JTransactionView transactionView;

  /**
   * Creates an instance of the GUIMainView JFrame to show the variety of view panels.
   */
  public GUIMainView() {
    super("Stock Market Simulator");
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    home = new JHome();
    currentPanel = home;
    createPortfolio = new JCreatePortfolioView("Create Portfolio");
    portfolioValue = new JValueAtDateView("Portfolio Value at Date");
    savePortfolio = new JSavePortfolioView("Save Portfolio");
    loadPortfolio = new JLoadPortfolioView("Load Portfolio");
    addStrategy = new JStrategyView("Add Strategy");
    buyStocksWithWeights = new JPanel();
    costBasis = new JCostBasisView("Cost Basis");
    compositionAtDate = new JCompositionView("Composition At Date");
    transactionView = new JTransactionView("Buy Sell Stock");
    this.add(home);
    this.pack();
  }

  @Override
  public void showPortfolioValue(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    portfolioValue.setPortfolioName(portfolioName);
    this.getContentPane().add(portfolioValue);
    this.pack();
  }

  @Override
  public void showSavePortfolio(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    savePortfolio.setPortfolioName(portfolioName);
    this.getContentPane().add(savePortfolio);
    this.pack();
  }

  @Override
  public void showAddPortfolio() {
    this.getContentPane().removeAll();
    this.repaint();
    this.getContentPane().add(createPortfolio);
    this.pack();
  }

  @Override
  public void showLoadPortfolio() {
    this.getContentPane().removeAll();
    this.repaint();
    this.getContentPane().add(loadPortfolio);
    this.pack();
  }

  @Override
  public void showBuySellStock(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    transactionView.setPortfolioName(portfolioName);
    this.getContentPane().add(transactionView);
    this.pack();
  }

  @Override
  public void showCostBasis(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    costBasis.setPortfolioName(portfolioName);
    this.getContentPane().add(costBasis);
    this.pack();
  }

  @Override
  public void showCompositionAtDate(String portfolioName, List<String> composition) {
    this.getContentPane().removeAll();
    this.repaint();
    compositionAtDate.setPortfolioName(portfolioName);
    this.getContentPane().add(compositionAtDate);
    this.pack();
  }

  @Override
  public void showAddStrategy(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    addStrategy.setPortfolioName(portfolioName);
    this.getContentPane().add(addStrategy);
    this.pack();
  }

  @Override
  public void showBuyStocksWithWeights() {
    // TODO: Complete implementation
  }

  @Override
  public void showPerformance(Map<String, Integer> values) {
    // TODO: Complete implementation
  }

  @Override
  public void showHome() {
    this.getContentPane().removeAll();
    this.repaint();
    this.add(home);
    this.pack();
  }

  @Override
  public void addActionListener(Features features) {
    compositionAtDate.addActionListener(features);
    home.addActionListener(features);
    createPortfolio.addActionListener(features);
    loadPortfolio.addActionListener(features);
    costBasis.addActionListener(features);
    transactionView.addActionListener(features);
    savePortfolio.addActionListener(features);
    portfolioValue.addActionListener(features);
  }

  @Override
  public void showInputPerformanceDates(String portfolioName) {
    // TODO: Complete implementation
  }

}
