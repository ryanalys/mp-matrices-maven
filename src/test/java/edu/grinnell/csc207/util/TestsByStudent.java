package edu.grinnell.csc207.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * A variety of tests that cover all the methods MatrixV0
 * 
 * @author Alyssa Ryan
 */
public class TestsByStudent {
  Integer nine = Integer.valueOf(9);
  Integer four = Integer.valueOf(4);
  Integer three = Integer.valueOf(3);
  Integer two = Integer.valueOf(2);
  
  @Test
  public void testGet() {
    Matrix<Integer> getTestMatrix = new MatrixV0<Integer>(2, 2, nine);
    assertEquals(nine, getTestMatrix.get(0, 1), "does get work");
  } //testGet

  @Test
  public void testSet() {
    Matrix<Integer> setTestMatrix = new MatrixV0<Integer>(2, 2, nine);
    setTestMatrix.set(1, 0, four);
    assertEquals(four, setTestMatrix.get(1, 0), "testing set");
  } //testSet

  @Test
  public void testInsertRow() {
    Matrix<Integer> rowTest = new MatrixV0<Integer>(2, 2, nine);

    rowTest.set(0, 0, four);
    rowTest.set(1, 0, four);

    rowTest.insertRow(2);
    assertEquals(3, rowTest.height(), "Do we have the right height?");

    Object[] expected = {four, four, nine};
    Object[] col1 = {rowTest.get(0, 0), rowTest.get(1, 0), rowTest.get(2, 0)};
    assertArrayEquals(expected, col1, "Did the row go in the right spot?");
  } //testInsertRow

  public void testInsertRowVals() {
    Matrix<Integer> rowTest = new MatrixV0<Integer>(2, 2, nine);

    Integer[] toInsert = {three, four};

    try {
      rowTest.insertRow(2, toInsert);
    } catch (Exception e) {
      //We failed
    }
    
    assertEquals(3, rowTest.height(), "Do we have the right height?");

    Object[] expected = {nine, nine, three, four};
    Object[] col1 = {rowTest.get(0, 0), rowTest.get(1, 0), rowTest.get(2, 0), rowTest.get(2, 1)};
    assertArrayEquals(expected, col1, "Did the row go in the right spot?");
  } //testInsertRowVals

  @Test
  public void testInsertCol() {
    Matrix<Integer> colTest = new MatrixV0<Integer>(2, 2, nine);

    colTest.set(0, 0, four);
    colTest.set(0, 1, four);

    colTest.insertCol(2);
    assertEquals(3, colTest.width(), "Do we have the right width?");

    Object[] expected = {four, four, nine};
    Object[] row1 = {colTest.get(0, 0), colTest.get(0, 1), colTest.get(0, 2)};
    assertArrayEquals(expected, row1, "Did the column go in the right spot?");
  } //testInsertCol

  @Test
  public void testInsertColVals() {
    Matrix<Integer> colTest = new MatrixV0<Integer>(2, 2, nine);

    Integer[] toInsert = {three, four};

    try {
      colTest.insertCol(2, toInsert);
    } catch (Exception e) {
      //We failed
    }
    
    assertEquals(3, colTest.width(), "Do we have the right width?");

    Object[] expected = {nine, nine, three, four};
    Object[] row1 = {colTest.get(0, 0), colTest.get(0, 1), colTest.get(0, 2), colTest.get(1, 2)};
    assertArrayEquals(expected, row1, "Did the column go in the right spot?");
  } //testInsertColVals

  @Test
  public void testDeleteCol() {
    Matrix<Integer> deleteCol = new MatrixV0<Integer>(3, 2, nine);
    deleteCol.set(0, 0, three);
    deleteCol.set(1, 0, three);
    deleteCol.set(0, 1, four);
    deleteCol.set(1, 1, four);
    
    deleteCol.deleteCol(2);

    Integer[] expected = {three, four};
    Integer[] actual = {deleteCol.get(0, 0), deleteCol.get(0, 1)};
    assertEquals(2, deleteCol.width());
    assertArrayEquals(expected, actual, "Did we delete the right row?");
  } //testDeleteRow

  @Test
  public void testDeleteRow() {
    Matrix<Integer> deleteRow = new MatrixV0<Integer>(2, 3, nine);

    deleteRow.set(0, 0, three);
    deleteRow.set(0, 1, three);
    deleteRow.set(1, 0, four);
    deleteRow.set(1, 1, four);

    deleteRow.deleteRow(0);

    Integer[] expected = {four, nine};
    Integer[] actual = {deleteRow.get(0, 0), deleteRow.get(1, 0)};

    assertEquals(2, deleteRow.height(), "Is the height correct?");
    assertArrayEquals(expected, actual, "Did we delete the right row?");
  } //testDeleteCol

  @Test
  public void testFillRegion() {
    Matrix<Integer> fill = new MatrixV0<Integer>(4, 4, nine);

    fill.fillRegion(0, 0, 2, 2, four);
    fill.fillRegion(0, 2, 2, 4, three);
    fill.fillRegion(2, 2, 4, 4, two);

    Integer[] expected = {four, three, nine, two};
    Integer[] actual = {fill.get(0,0), fill.get(0, 3), fill.get(3, 0), fill.get(3,3)};

    assertArrayEquals(expected, actual, "Did we fill the right values?");
  } //testFillRegion

  @Test
  public void testFillLine() {
    Matrix<Integer> fillLine = new MatrixV0<Integer>(4, 4, nine);
    
    fillLine.fillLine(0, 0, 1, 1, 4, 4, three);
    fillLine.fillLine(0, 3, 1, 1, 1, 4, four);
    fillLine.fillLine(3, 0, 1, 1, 4, 1, two);

    Integer[] expected = {three, four, two, three};
    Integer[] actual = {fillLine.get(0, 0), fillLine.get(0, 3), fillLine.get(3, 0), fillLine.get(3, 3)};
    assertArrayEquals(expected, actual, "Did the values go in the correct places?");
  } //testFillLine

  @Test
  public void testEquals() {
    Matrix<Integer> first = new MatrixV0<Integer>(2, 2, nine);
    Matrix<Integer> second = new MatrixV0<Integer>(2, 2, nine);

    assertEquals(true, first.equals(second), "Same matrices are equal");

    first.set(0, 0, three);
    assertEquals(false, first.equals(second), "Change one value -> not equal");

    first.set(0, 0, nine);
    assertEquals(true, first.equals(second), "Once again the same");
  } //testEquals

  @SuppressWarnings("unchecked")
  @Test
  public void testClone() {
    Matrix<Integer> original = new MatrixV0<Integer>(2, 2, nine);
    Matrix<Integer> newMatrix = original.clone();
    
    assertEquals(true, original.equals(newMatrix), "Is the clone the same?");
  } //testClone
} //class TestsByStudent
