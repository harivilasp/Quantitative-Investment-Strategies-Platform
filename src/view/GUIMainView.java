package view;

import java.awt.*;
import java.util.List;
import java.util.Map;

import javax.swing.*;

import controller.Features;

public class GUIMainView extends JFrame implements GUIView{

  JPanel portfolioValue;
  JCreatePortfolioView createPortfolio;
  JPanel savePortfolio;
  JLoadPortfolioView loadPortfolio;
  JStrategyView addStrategy;
  JPanel buyStocksWithWeights;
  JCostBasisView costBasis;
  JCompositionView compositionAtDate;
  JHome home;
  JPanel currentPanel;
  JTransactionView transactionView;
  public GUIMainView(){
    super("Stock Market Simulator");
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    home = new JHome();
    currentPanel = home;
    createPortfolio = new JCreatePortfolioView("Create Portfolio");
    portfolioValue = new JPanel();
    savePortfolio = new JPanel();
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

  }

  @Override
  public void showSavePortfolio() {

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
    this.getContentPane().add(transactionView);
    this.pack();
  }

  @Override
  public void showCostBasis(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    this.getContentPane().add(costBasis);
    this.pack();
  }

  @Override
  public void showCompositionAtDate(String portfolioName,List<String> composition) {
    this.getContentPane().removeAll();
    this.repaint();
    this.getContentPane().add(compositionAtDate);
    this.pack();
  }

  @Override
  public void showAddStrategy(String portfolioName) {
    this.getContentPane().removeAll();
    this.repaint();
    this.getContentPane().add(addStrategy);
    this.pack();
  }

  @Override
  public void showBuyStocksWithWeights() {

  }

  @Override
  public void showPerformance(Map<String, Integer> values) {

  }

  @Override
  public void showHome(){
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
  }

}
