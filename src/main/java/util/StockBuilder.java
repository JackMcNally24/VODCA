package util;

public interface StockBuilder<Doc> {

  /**
   * Constructs a final document.
   *
   * @return the newly constructed document
   */
  Doc build();


  /**
   * Adds the given stock and its valuation to the ArrayList of the model.
   */
  StockBuilder<Doc> addFiveInputs(String ticker, double price, double growth, double curAvgEst, double nextAvgEst, double dividends);

  /**
   * Adds the given stock and its valuation to the HashMap of the model.
   */
  StockBuilder<Doc> addEightInputs(String ticker, double price, double growth, double curAvgEst, double nextAvgEst, double dividends);
}
