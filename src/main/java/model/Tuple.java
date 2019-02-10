package model;

public class Tuple implements Comparable{
  private String ticker;
  private double valuation;

  public Tuple(String ticker, double valuation) {
    this.ticker = ticker;
    this.valuation = valuation;
  }

  public String getTicker() {
    return ticker;
  }

  public double getValuation() {
    return valuation;
  }


  @Override
  public int compareTo(Object o) {
    Tuple tuple = (Tuple) o;
    return (int) Math.round(this.valuation - tuple.getValuation());
  }
}
