package model;

import java.util.ArrayList;

public class ModelUtils {
  // constructor stuff
  public ModelUtils() {

  }

  // formula 1: future growths:
  public ArrayList<Double> futureGrowth(double growth, double growthdiscount) {
    ArrayList<Double> keep = new ArrayList<Double>();
    keep.add(growth);
    for(int i = 1; i< 11; i++) {
      keep.add(keep.get(i-1) - (growthdiscount * keep.get(i- 1)));
    }
    return keep;
  }

  // formula 2: earnings estimates
  public ArrayList<Double> earningsEst(ArrayList<Double> growth, double fest, double sest) {
    ArrayList<Double> keep = new ArrayList<Double>();
    keep.add(fest);
    keep.add(sest);
    for(int i = 2; i < 11; i++) {
      keep.add((keep.get(i-1) * growth.get(i-1)) + keep.get(i-1));
    }
    return keep;
  }

  // formula 3: dividends
  public ArrayList<Double> dividend(double dividend, double growthrate) {
    ArrayList<Double> keep = new ArrayList<Double>();
    keep.add(dividend);
    for(int i = 1; i < 11; i++) {
      keep.add((keep.get(i - 1) * growthrate) + keep.get(i-1));
    }
    return keep;
  }

  // formula 4: projected price
  public ArrayList<Double> projPrice (ArrayList<Double> est, double pe) {
    ArrayList<Double> keep = new ArrayList<Double>();
    keep.add((double) 0);
    for (int i = 1; i < 11; i++) {
      keep.add(est.get(i) * pe);
    }
    return keep;
  }

  // formula 5: 5 year hold
  public ArrayList<Double> fiveYearHold(ArrayList<Double> div, double p1, double p2) {
    ArrayList<Double> keep = new ArrayList<Double>();
    for(int i= 0; i< 4; i++) {
      keep.add(div.get(i));
    }
    keep.add(p1 + p2);
    for(int i= 5; i < 11; i++) {
      keep.add((double) 0);
    }
    return keep;
  }

  // formula 6: 10 year hold
  public ArrayList<Double> tenYearHold(ArrayList<Double> div, double p1, double p2) {
    ArrayList<Double> keep = new ArrayList<Double>();
    for(int i= 0; i< 9; i++) {
      keep.add(div.get(i));
    }
    keep.add(p1 + p2);
    for(int i = 9; i < 11; i++) {
      keep.add((double)0);
    }
    return keep;
  }

  // formula 7: npv calculations
  public double npv(ArrayList<Double> cashflow, double r) {
    double k = 0;
    for(int i = 0; i < 11; i++) {
      k = k + (cashflow.get(i) / (Math.pow((1 + r), i)));
    }
    return k;
  }
}
