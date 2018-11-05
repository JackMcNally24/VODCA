package model.shape;

import java.awt.Color;

/**
 * An AShape is any object that can be seen in the view of the animator.
 * The int id is the number each AShape gets so it can be identified in the model class.
 * The Posn position is where on the screen the AShape appears.
 * The Posn size is the size of the AShape on the screen.
 * The Color color is the size of the AShape on the screen.
 */
public class AShape implements IShape {
  private int id;
  private Posn position;
  private Posn size;
  private Color color;

  /**
   * Constructs a {@code AShape} object.
   *
   * @param id each AShape gets it's own id so it can be identified in the model class.
   * @param position is where on the screen the AShape appears.
   * @param size is the size of the AShape on the screen.
   * @param color is the color of the AShape on the screen.
   */
  AShape(int id, Posn position, Posn size, Color color) {
    this.id = id;
    if (position.getX() < 0 || position.getY() < 0) {
      throw new IllegalArgumentException("Your position must be a positive number!");
    }
    else {
      this.position = position;
    }
    if (size.getX() <= 0 || size.getY() <= 0) {
      throw new IllegalArgumentException("Your size has to have positive dimensions!");
    }
    else {
      this.size = size;
    }
    try {
      this.color = color;
    }
    catch (Exception e) {
      throw new IllegalArgumentException("That is an invalid Color!");
    }
  }

  @Override
  public int getId() {
    return this.id;
  }

  @Override
  public Posn getPosition() {
    return this.position;
  }

  @Override
  public Posn getSize() {
    return this.size;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void setPosition(Posn position) {
    this.position = position;
  }

  @Override
  public void setSize(Posn size) {
    this.size = size;
  }

  @Override
  public void setColor(Color color) {
    this.color = color;
  }
}
