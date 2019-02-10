package view;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;

import model.ModelUtils;

public class EightPanel extends JPanel {
  private HashMap<String, ArrayList<Double>> eight;
  private String ticker;
  private ArrayList<ArrayList<Double>> table;

  public EightPanel() {
    super();
    this.setBackground(Color.BLACK);
    this.eight = new HashMap<>();
    this.table = new ArrayList<ArrayList<Double>>();
    ticker = "";
  }


  public void setEight(HashMap<String, ArrayList<Double>> eight) {
    this.eight = eight;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public double eightInputs(double price, double growth, double avgthis, double avgnext, double dividends, double growthdiscount,
                            double discountrate, double pevalue) {
    table.add(new ModelUtils().futureGrowth(growth, growthdiscount));
    table.add(new ModelUtils().earningsEst(table.get(0), avgthis, avgnext));
    table.add(new ModelUtils().dividend(dividends, growth));
    table.add(new ModelUtils().projPrice(table.get(1), pevalue));
    table.add(new ModelUtils().fiveYearHold(table.get(2), table.get(3).get(4), table.get(2).get(4)));
    table.add(new ModelUtils().tenYearHold(table.get(2), table.get(3).get(4), table.get(2).get(4)));
    double fiveyearnpv = new ModelUtils().npv(table.get(4), discountrate);
    double tenyearnpv = new ModelUtils().npv(table.get(5), discountrate);
    double npv =  (fiveyearnpv + tenyearnpv) /2 ;
    double answer = (npv - price) / npv * 100;
    return answer;
  }

  @Override
  protected void paintComponent(Graphics g1) {
    Graphics2D g = (Graphics2D) g1;
    super.paintComponent(g);
    if (!ticker.equals("")) {
      String ticker = this.ticker;
      ArrayList<Double> dubs = eight.get(ticker);
      double valuation = eightInputs(dubs.get(0), dubs.get(1), dubs.get(2), dubs.get(3), dubs.get(4), dubs.get(5),
              dubs.get(6), dubs.get(7));
      g.setColor(Color.WHITE);
      g.drawString(ticker, 300, 0);
      if (valuation >= 0) {
        g.setColor(Color.GREEN);
      } else {
        g.setColor(Color.RED);
      }
      g.drawString(Double.toString(valuation), 400, 0);
    }
  }
}
