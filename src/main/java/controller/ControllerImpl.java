package controller;

import java.io.File;

import model.Builder;
import model.IModel;
import model.ModelImpl;
import util.StockBuilder;
import util.StockReader;
import view.IView;

public class ControllerImpl implements IController{

  private IView view;
  private ModelImpl model;
  private File file;
  private StockReader sR;
  private Appendable ap;

  /**
   * A Constructor for the controller that forms the model based on the readable.
   *
   * @param view The View that we want to draw on.
   * @param file The readable file that will form the model.
   * @param ap The appendable that is being written to.
   * @throws IllegalArgumentException If anything is null.
   */
  public ControllerImpl(IView view, File file, Appendable ap) {
    StockBuilder<ModelImpl> builder = new Builder();
    this.model = sR.parseFile(file, builder);
    if (view == null) {
      throw new IllegalArgumentException("Your view cannot be null!");
    } else {
      this.view = view;
    }
    if (file == null) {
      throw new IllegalArgumentException("Your Readable cannot be null!");
    } else {
      this.file = file;
    }
    if (ap == null) {
      throw new IllegalArgumentException("Your Appendable cannot be null!");
    } else {
      this.ap = ap;
    }
  }

  @Override
  public void externalWriter() {
    ap = view.createAppendableView(ap);
      try {
        this.view.makeVisible();
      } catch (Exception e) {
        throw new IllegalStateException();
      }
      view.setStocks(model.getStocks());
      view.setEight(model.getEight());
      view.refresh();
  }
}
