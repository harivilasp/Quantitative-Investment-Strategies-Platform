package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import controller.Features;

public class GUIMainView extends JFrame implements GUIView {

  JValueAtDateView portfolioValue;
  JCreatePortfolioView createPortfolio;
  JSavePortfolio savePortfolio;
  JLoadPortfolioView loadPortfolio;
  JStrategyView addStrategy;
  JBuyWeightageView buyStocksWithWeights;
  JCostBasisView costBasis;
  JCompositionView compositionAtDate;
  JHome home;
  JTransactionView transactionView;
  JPerfGraphView perfGraphView;

  public GUIMainView() {
    super("Stock Market Simulator");
    this.setPreferredSize(new Dimension(850, 650));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    home = new JHome();
    createPortfolio = new JCreatePortfolioView("Create Portfolio");
    portfolioValue = new JValueAtDateView("Portfolio Value at Date");
    savePortfolio = new JSavePortfolio("Save Portfolio");
    loadPortfolio = new JLoadPortfolioView("Load Portfolio");
    addStrategy = new JStrategyView("Add Strategy");
    buyStocksWithWeights = new JBuyWeightageView("Buy with stocks");
    costBasis = new JCostBasisView("Cost Basis");
    compositionAtDate = new JCompositionView("Composition At Date");
    transactionView = new JTransactionView("Buy Sell Stock");
    perfGraphView = new JPerfGraphView("Performance over time");
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
  public void showBuyStocksWithWeights(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    buyStocksWithWeights.setPortfolioName(portfolioName);
    this.getContentPane().add(buyStocksWithWeights);
    this.pack();
  }

  @Override
  public void showPerformance(Map<String, Integer> values) {

  }

  @Override
  public void showHome(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    home.setPortfolioName(portfolioName);
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
    addStrategy.addActionListener(features);
    buyStocksWithWeights.addActionListener(features);
    perfGraphView.addActionListener(features);
  }

  @Override
  public void showInputPerformanceDates(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    perfGraphView.setPortfolioName(portfolioName);
    this.add(perfGraphView);
    this.pack();
  }

}
