package model.shape;

import java.awt.Color;

/**
 * This interface represents the operations offered by a shape.
 */
public interface IShape {

  /**
   * A getter for the id number of the IShape.
   *
   * @return the id of the IShape
   */
  int getId();

  /**
   * A getter for the Position of the IShape.
   *
   * @return a Posn representing the position of the IShape.
   */
  Posn getPosition();

  /**
   * A getter for the size of the IShape.
   *
   * @return a Posn representing the width and height of the IShape.
   */
  Posn getSize();

  /**
   * A getter for the color of the IShape.
   *
   * @return the color of the IShape
   */
  Color getColor();

  /**
   * This method sets the position of the IShape to the given Posn.
   *
   * @param position is the new position of the IShape
   */
  void setPosition(Posn position);

  /**
   * This method sets the size of the IShape to the given Posn.
   *
   * @param size is the new size of the IShape
   */
  void setSize(Posn size);

  /**
   * This method sets the color of the IShape to the given Color.
   *
   * @param color is the new color of the IShape
   */
  void setColor(Color color);
}
