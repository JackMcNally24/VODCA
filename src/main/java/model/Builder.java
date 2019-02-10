package model;

import util.StockBuilder;

public final class Builder implements StockBuilder<ModelImpl> {

  ModelImpl model;

  public Builder() {
    this.model = new ModelImpl();
  }

  @Override
  public ModelImpl build() {
    return model;
  }

  @Override
  public StockBuilder<ModelImpl> addFiveInputs(String ticker, double price, double growth, double curAvgEst, double nextAvgEst, double dividends) {
    model.fiveInputs(ticker, price, growth, curAvgEst, nextAvgEst, dividends);
    return this;
  }

  @Override
  public StockBuilder<ModelImpl> addEightInputs(String ticker, double price, double growth, double curAvgEst, double nextAvgEst, double dividends) {
    model.startEight(ticker, price, growth, curAvgEst, nextAvgEst, dividends);
    return this;
  }

}
