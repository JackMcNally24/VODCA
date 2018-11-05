package model.shape;

import java.awt.Color;

/**
 * An Oval an AShape that is drawn with rounded edges.
 */
public class Oval extends AShape {

  /**
   * Constructs a {@code Oval} object.
   *
   * @param id each AShape gets it's own id so it can be identified in the model class.
   * @param position is where on the screen the AShape appears.
   * @param size is the size of the AShape on the screen.
   * @param color is the color of the AShape on the screen.
   */
  public Oval(int id, Posn position, Posn size, Color color) {
    super(id, position, size, color);
  }
}
