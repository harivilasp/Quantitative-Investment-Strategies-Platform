package view;

import java.util.List;
import java.util.Map;

import javax.swing.*;

public class GUIMainView implements GUIView{

  JPanel portfolioValue;
  JPanel savePortfolio;
  JPanel addPortfolio;
  JPanel loadPortfolio;
  JPanel buySellStock;
  JPanel addStrategy;
  JPanel buyStocksWithWeights;
  JPanel costBasis;
  JPanel compositionAtDate;
  JPanel home;
  JPanel currentPanel;

  public GUIMainView(){
    home = new JPanel();
    currentPanel = home;
    portfolioValue = new JPanel();
    savePortfolio = new JPanel();
    addPortfolio = new JPanel();
    loadPortfolio = new JPanel();
    buySellStock = new JPanel();
    addStrategy = new JPanel();
    buyStocksWithWeights = new JPanel();
    costBasis = new JPanel();
    compositionAtDate = new JPanel();
  }

  @Override
  public void showPortfolioValue() {

  }

  @Override
  public void showSavePortfolio() {

  }

  @Override
  public void showAddPortfolio() {

  }

  @Override
  public void showLoadPortfolio() {

  }

  @Override
  public void showBuySellStock() {

  }

  @Override
  public void showCostBasis() {

  }

  @Override
  public void showCompositionAtDate(List<String> composition) {

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
}
