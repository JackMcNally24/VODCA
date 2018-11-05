import org.junit.Test;


import java.awt.Color;
import java.util.ArrayList;

import model.AnimatorModel;
import model.AnimatorModelImpl;
import model.Motion;
import model.shape.IShape;
import model.shape.Oval;
import model.shape.Posn;
import model.shape.Rectangle;

import static org.junit.Assert.assertEquals;

/**
 * The test class for the Animator Model.
 */
public class AnimatorModelImplTest {

  //tests to make sure that the implementation can move a shape from one place to another
  @Test
  public void testSingleMove() {
    IShape c1 = new Oval(1, new Posn(100, 100), new Posn(100, 100), new Color(255, 0, 0));
    //Shape c1 moves from (100,100) to (300,300) and does not change color or size from
    //t=0 to t=100
    Motion m1 = new Motion(1, new Posn(300, 300), new Posn(0, 100), new Posn(100, 100),
            new Color(255, 0, 0));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(c1);
    ex1.addMotion(m1);
    ex1.applyMotions();
    assertEquals("shape id: 1\n" +
            "motion shape id: 1 0 100 100 100 100 255 0 0   100 300 300 100 100 255 0 0\n",
            ex1.getBuild());
  }

  //tests to see if multiple motions applied to 1 shape will still get it from point A to point B
  @Test
  public void testMultipleMoves() {
    IShape c1 = new Oval(1, new Posn(100, 100), new Posn(100, 100), new Color(255, 0, 0));
    //Shape c1 moves from (100,100) to (150,150) and does not change color or size from
    //t=0 to t=100
    Motion m1 = new Motion(1, new Posn(150, 150), new Posn(0, 100), new Posn(100, 100),
            new Color(255, 0, 0));
    //Shape c1 moves from (150,150) to (300,300) and does not change color or size from
    //t=100 to t=150
    Motion m2 = new Motion(1, new Posn(300, 300), new Posn(100, 150), new Posn(100, 100),
            new Color(255, 0, 0));
    //Shape c1 moves from (300,300) to (100,100) and does not change color or size from
    //t=150 to t=155
    Motion m3 = new Motion(1, new Posn(100, 100), new Posn(150, 155), new Posn(100, 100),
            new Color(255, 0, 0));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(c1);
    ex1.addMotion(m1);
    ex1.addMotion(m2);
    ex1.addMotion(m3);
    ex1.applyMotions();
    assertEquals("shape id: 1\n" +
                    "motion shape id: 1 0 100 100 100 100 255 0 0   " +
                    "100 150 150 100 100 255 0 0\n" +
                    "shape id: 1\n" +
                    "motion shape id: 1 100 150 150 100 100 255 0 0   " +
                    "150 300 300 100 100 255 0 0\n" +
                    "shape id: 1\n" +
                    "motion shape id: 1 150 300 300 100 100 255 0 0   " +
                    "155 100 100 100 100 255 0 0\n",
            ex1.getBuild());
  }


  //tests if the color changes correctly from the motion
  @Test
  public void testMoveAndColorChange() {
    IShape r1 = new Rectangle(3, new Posn(0, 0), new Posn(50, 100), new Color(255, 0, 0));
    Motion m1 = new Motion(3, new Posn(300, 0), new Posn(0, 100), new Posn(50, 100),
            new Color(155, 0, 100));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(r1);
    ex1.addMotion(m1);
    ex1.applyMotions();
    assertEquals("shape id: 3\n" +
            "motion shape id: 3 0 0 0 50 100 255 0 0   100 300 0 50 100 155 0 100\n",
            ex1.getBuild());
  }

  //tests if the size changes correctly from the motion
  //and if color can change twice between 2 motions over long periods of time
  @Test
  public void testMoveAndSizeChange() {
    IShape r1 = new Rectangle(500, new Posn(0, 0), new Posn(50, 100), new Color(255, 0, 0));
    Motion m1 = new Motion(500, new Posn(3213, 234), new Posn(0, 100), new Posn(100, 5),
            new Color(0, 0, 255));
    Motion m2 = new Motion(500, new Posn(10, 10), new Posn(100, 1000), new Posn(10, 50),
            new Color(0, 255, 0));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(r1);
    ex1.addMotion(m1);
    ex1.addMotion(m2);
    ex1.applyMotions();
    assertEquals("shape id: 500\n" +
                    "motion shape id: 500 0 0 0 50 100 255 0 0   100 3213 234 100 5 0 0 255\n" +
                    "shape id: 500\n" +
                    "motion shape id: 500 100 3213 234 100 5 0 0 255   1000 10 10 10 50 0 255 0\n",
            ex1.getBuild());
  }

  //tests if multiple shapes can be moved by multiple motions
  @Test
  public void testMultipleShapes() {
    IShape r1 = new Rectangle(1, new Posn(0, 0), new Posn(50, 100), new Color(255, 0, 0));
    IShape r2 = new Rectangle(2, new Posn(25, 50), new Posn(200, 100), new Color(0, 255, 0));
    IShape c1 = new Oval(3, new Posn(100, 100), new Posn(100, 100), new Color(255, 0, 255));
    //Shape r1 moves from (0,0) to (10,10) from t=0 to t=10, and does not change size or color
    Motion m1 = new Motion(1, new Posn(10, 10), new Posn(0, 10), new Posn(50, 100),
            new Color(255, 0, 0));
    //Shape r1 moves from (10,10) to (100,10) from t=10 to t=20, and does not change size or color
    Motion m2 = new Motion(1, new Posn(100, 10), new Posn(10, 20), new Posn(50, 100),
            new Color(255, 0, 0));
    //Shape r2 moves from (25,50) to (100, 50) from t=20 to t=100, shrinks its width by 100 and
    // does not change color
    Motion m3 = new Motion(2, new Posn(100, 50), new Posn(20, 100), new Posn(100, 100),
            new Color(0, 255, 0));
    //shape c1 does not move but its height grows by 50 and changes from blue to green from t=100
    // to t=200
    Motion m4 = new Motion(3, new Posn(100, 100), new Posn(100, 200), new Posn(100, 150),
            new Color(0, 255, 0));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(r1);
    ex1.addShape(r2);
    ex1.addShape(c1);
    ex1.addMotion(m1);
    ex1.addMotion(m2);
    ex1.addMotion(m3);
    ex1.addMotion(m4);
    ex1.applyMotions();

    assertEquals("shape id: 1\n" +
            "motion shape id: 1 0 0 0 50 100 255 0 0   10 10 10 50 100 255 0 0\n" +
            "shape id: 1\n" +
            "motion shape id: 1 10 10 10 50 100 255 0 0   20 100 10 50 100 255 0 0\n" +
            "shape id: 2\n" +
            "motion shape id: 2 20 25 50 200 100 0 255 0   100 100 50 100 100 0 255 0\n" +
            "shape id: 3\n" +
            "motion shape id: 3 100 100 100 100 100 255 0 255   200 100 100 100 150 0 255 0\n",
            ex1.getBuild());
  }

  //tests if applyMotions is called when there are no shapes or motions
  @Test
  public void testNoMotions() {
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.applyMotions();
    assertEquals("", ex1.getBuild());
  }

  //tests
  @Test (expected = IllegalArgumentException.class)
  public void testNonexistantShape() {
    IShape r1 = new Rectangle(1, new Posn(0, 0), new Posn(50, 100), new Color(255, 0, 0));

    Motion m1 = new Motion(100, new Posn(10, 10), new Posn(0, 10), new Posn(50, 100),
            new Color(255, 0, 0));
    AnimatorModel ex1 = new AnimatorModelImpl();
    ex1.addShape(r1);
    ex1.addMotion(m1);
    ex1.applyMotions();
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadShapePos() {
    IShape yuck = new Rectangle(1, new Posn(-100, -100), new Posn(100,100),
            new Color(255,0,0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadShapeSize() {
    IShape yuck = new Rectangle(1, new Posn(10, 0), new Posn(0,100),
            new Color(255,0,0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadShapeColor() {
    IShape yuck = new Rectangle(1, new Posn(10, 0), new Posn(100,100),
            new Color(1000,1000,1000));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadMotionPos() {
    Motion m1 = new Motion(1, new Posn(-10, 10), new Posn(0, 10), new Posn(50, 100),
            new Color(255, 0, 0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadMotionTime() {
    Motion m1 = new Motion(1, new Posn(10, 10), new Posn(20, 10), new Posn(50, 100),
            new Color(255, 0, 0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadMotionSize() {
    Motion m1 = new Motion(1, new Posn(10, 10), new Posn(20, 30), new Posn(50, 0),
            new Color(255, 0, 0));
  }

  @Test (expected = IllegalArgumentException.class)
  public void testBadMotionColor() {
    Motion m1 = new Motion(1, new Posn(10, 10), new Posn(0, 10), new Posn(50, 100),
            new Color(-100, 2130, 0));
  }

  //tests that our constructor that takes in 2 parameters works correctly
  @Test
  public void testOtherConstructor() {
    ArrayList<IShape> shapes = new ArrayList<IShape>();
    ArrayList<Motion> motions = new ArrayList<Motion>();
    IShape c1 = new Oval(1, new Posn(100, 100), new Posn(100, 100), new Color(255, 0, 0));
    //Shape c1 moves from (100,100) to (300,300) and does not change color or size from
    //t=0 to t=100
    Motion m1 = new Motion(1, new Posn(300, 300), new Posn(0, 100), new Posn(100, 100),
            new Color(255, 0, 0));
    shapes.add(c1);
    motions.add(m1);
    AnimatorModel ex1 = new AnimatorModelImpl(shapes, motions);
    ex1.applyMotions();
    assertEquals("shape id: 1\n" +
            "motion shape id: 1 0 100 100 100 100 255 0 0   100 300 300 100 100 255 0 0\n",
            ex1.getBuild());
  }
}
