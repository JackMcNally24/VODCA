package model.shape;

/**
 * A Posn is an object that consists of two floats.
 */
public class Posn {
  private float x;
  private float y;

  /**
   * Constructs a {@code Posn} object.
   *
   * @param x is the first value in the pair that is a Posn.
   * @param y is the second value in the pair that is a Posn.
   */
  public Posn(float x, float y) {
    this.x = x;
    this.y = y;
  }

  /**
   * The getter for the x value fo the Posn.
   *
   * @return a float representing the x value of the Posn
   */
  public float getX() {
    return x;
  }

  /**
   * The getter for the y value of the Posn.
   *
   * @return a float representing the y value of the Posn
   */
  public float getY() {
    return y;
  }
}
