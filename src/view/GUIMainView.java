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
  JPanel loadPortfolio;
  JPanel addStrategy;
  JPanel buyStocksWithWeights;
  JPanel costBasis;
  JCompositionView compositionAtDate;
  Home home;
  JPanel currentPanel;
  JTransactionView transactionView;
  public GUIMainView(){
    super("Stock Market Simulator");
    this.setPreferredSize(new Dimension(450, 500));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);

    home = new Home();
    currentPanel = home;
    createPortfolio = new JCreatePortfolioView("Create Portfolio");
    portfolioValue = new JPanel();
    savePortfolio = new JPanel();
    loadPortfolio = new JPanel();
    addStrategy = new JPanel();
    buyStocksWithWeights = new JPanel();
    costBasis = new JPanel();
    compositionAtDate = new JCompositionView("Composition At Date");
    transactionView = new JTransactionView("Buy Sell Stock");
    this.add(home);
//    this.add(createPortfolio);
//    this.add(savePortfolio);
//    this.add(loadPortfolio);
//
//    this.add(addStrategy);
//    this.add(buyStocksWithWeights);
//
//    this.add(costBasis);
//    this.add(compositionAtDate);
//    this.add(transactionView);

//    home.setVisible(true);
//    createPortfolio.setVisible(false);
//    savePortfolio.setVisible(false);
//    addStrategy.setVisible(false);
//    buyStocksWithWeights.setVisible(false);
//
//    transactionView.setVisible(false);
//    compositionAtDate.setVisible(false);

    this.pack();
  }

  @Override
  public void showPortfolioValue() {

  }

  @Override
  public void showSavePortfolio() {

  }

  @Override
  public void showAddPortfolio() {
    this.getContentPane().removeAll();
    this.add(createPortfolio);
    this.pack();
//    home.setVisible(false);
//    createPortfolio.setVisible(true);
  }

  @Override
  public void showLoadPortfolio() {

  }

  @Override
  public void showBuySellStock() {
    home.setVisible(false);
    transactionView.setVisible(true);
  }

  @Override
  public void showCostBasis() {

  }

  @Override
  public void showCompositionAtDate(List<String> composition) {
    home.setVisible(false);
    compositionAtDate.setVisible(true);
  }

  @Override
  public void showAddStrategy() {

  }

  @Override
  public void showBuyStocksWithWeights() {

  }

  @Override
  public void showPerformance(Map<String, Integer> values) {

  }

  @Override
  public void showHome(){

  }

  @Override
  public void addActionListener(Features features) {
    compositionAtDate.addActionListener(features);
    home.addActionListener(features);
    createPortfolio.addActionListener(features);
  }

}
