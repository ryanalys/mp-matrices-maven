package edu.grinnell.csc207.util;

/**
 * An implementation of two-dimensional matrices.
 *
 * @author Alyssa Ryan
 * @author Samuel A. Rebelsky
 *
 * @param <T>
 *   The type of values stored in the matrix.
 */
public class MatrixV0<T> implements Matrix<T> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+
  /**
   * Holds all of the values of the matrix.
   */
  Object[][] values;
  /**
   * The number of rows in the matrix.
   */
  int rowSize;
  /**
   * The number of columns in the matrix.
   */
  int colSize;
  /**
   * The default value of the matrix.
   */
  T defVal;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new matrix of the specified width and height with the
   * given value as the default.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   * @param def
   *   The default value, used to fill all the cells.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height, T def) {
    if (width < 0 || height < 0) {
      throw new NegativeArraySizeException();
    } //if

    this.rowSize = height;
    this.colSize = width;
    this.defVal = def;
    this.values = new Object[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        values[i][j] = def;
      } //for
    } //for
  } // MatrixV0(int, int, T)

  /**
   * Create a new matrix of the specified width and height with
   * null as the default value.
   *
   * @param width
   *   The width of the matrix.
   * @param height
   *   The height of the matrix.
   *
   * @throws NegativeArraySizeException
   *   If either the width or height are negative.
   */
  public MatrixV0(int width, int height) {
    this(width, height, null);
  } // MatrixV0

  // +--------------+------------------------------------------------
  // | Core methods |
  // +--------------+

  /**
   * Get the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   *
   * @return the value at the specified location.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  @SuppressWarnings("unchecked")
  public T get(int row, int col) {
    if ((this.rowSize < row) || (this.colSize < col)) {
      throw new IndexOutOfBoundsException();
    } //if
    return (T) values[row][col];
  } // get(int, int)

  /**
   * Set the element at the given row and column.
   *
   * @param row
   *   The row of the element.
   * @param col
   *   The column of the element.
   * @param val
   *   The value to set.
   *
   * @throws IndexOutOfBoundsException
   *   If either the row or column is out of reasonable bounds.
   */
  public void set(int row, int col, T val) {
    if ((this.rowSize < row) || (this.colSize < col)) {
      throw new IndexOutOfBoundsException();
    } //if
    values[row][col] = val;
  } // set(int, int, T)

  /**
   * Determine the number of rows in the matrix.
   *
   * @return the number of rows.
   */
  public int height() {
    return this.rowSize;
  } // height()

  /**
   * Determine the number of columns in the matrix.
   *
   * @return the number of columns.
   */
  public int width() {
    return this.colSize;
  } // width()

  /**
   * Insert a row filled with the default value.
   *
   * @param row
   *   The number of the row to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   */
  public void insertRow(int row) {
    if ((row < 0) || (row > this.rowSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    //Create the new row to insert
    Object[] newRow = new Object[this.colSize];
    for (int i = 0; i < this.colSize; i++) {
      newRow[i] = this.defVal;
    } //for

    //Create a new array to replace values, with the new row
    Object[][] newVals = new Object[this.rowSize + 1][this.colSize];
    for (int i = 0; i < row; i++) {
      newVals[i] = this.values[i];
    } //for
    newVals[row] = newRow;
    for (int i = (row + 1); i < (this.rowSize + 1); i++) {
      newVals[i] = this.values[i - 1];
    } //for

    this.values = newVals;

    //Increase rowSize
    this.rowSize++;
  } // insertRow(int)

  /**
   * Insert a row filled with the specified values.
   *
   * @param row
   *   The number of the row to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than the height.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the width of the matrix.
   */
  public void insertRow(int row, T[] vals) throws ArraySizeException {
    if ((row < 0) || (row > this.rowSize)) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.colSize) {
      throw new ArraySizeException();
    } //if

    //Create the new row to insert
    Object[] newRow = new Object[this.colSize];
    for (int i = 0; i < this.colSize; i++) {
      newRow[i] = vals[i];
    } //for

    //Create a new array to replace vals, with the new row
    Object[][] newVals = new Object[this.rowSize + 1][this.colSize];
    for (int i = 0; i < row; i++) {
      newVals[i] = this.values[i];
    } //for
    newVals[row] = newRow;
    for (int i = (row + 1); i < (this.rowSize + 1); i++) {
      newVals[i] = this.values[i - 1];
    } //for

    this.values = newVals;

    //Increase rowSize
    this.rowSize++;
  } // insertRow(int, T[])

  /**
   * Insert a column filled with the default value.
   *
   * @param col
   *   The number of the column to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   */
  public void insertCol(int col) {
    if ((col < 0) || (col > this.colSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    Object[][] newVals = new Object[this.rowSize][this.colSize + 1];
    for (int i = 0; i < this.rowSize; i++) {
      Object[] newRow = new Object[this.colSize + 1];

      for (int j = 0; j < col; j++) {
        newRow[j] = this.values[i][j];
      } //for

      newRow[col] = this.defVal;

      for (int j = (col + 1); j < (this.colSize + 1); j++) {
        newRow[j] = this.values[i][j - 1];
      } //for
      newVals[i] = newRow;
    } //for

    this.values = newVals;
    this.colSize++;
  } // insertCol(int)

  /**
   * Insert a column filled with the specified values.
   *
   * @param col
   *   The number of the column to insert.
   * @param vals
   *   The values to insert.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than the width.
   * @throws ArraySizeException
   *   If the size of vals is not the same as the height of the matrix.
   */
  public void insertCol(int col, T[] vals) throws ArraySizeException {
    if ((col < 0) || (col > this.colSize)) {
      throw new IndexOutOfBoundsException();
    } else if (vals.length != this.rowSize) {
      throw new ArraySizeException();
    } //if

    Object[][] newVals = new Object[this.rowSize][this.colSize + 1];
    for (int i = 0; i < this.rowSize; i++) {
      Object[] newRow = new Object[colSize + 1];

      for (int j = 0; j < col; j++) {
        newRow[j] = this.values[i][j];
      } //for

      newRow[col] = vals[i];

      for (int j = (col + 1); j < (this.colSize + 1); j++) {
        newRow[j] = this.values[i][j - 1];
      } //for

      newVals[i] = newRow;
    } //for

    this.values = newVals;
    this.colSize++;
  } // insertCol(int, T[])

  /**
   * Delete a row.
   *
   * @param row
   *   The number of the row to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the row is negative or greater than or equal to the height.
   */
  public void deleteRow(int row) {
    if ((row < 0) || (row >= this.rowSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    Object[][] newVals = new Object[this.rowSize - 1][this.colSize];
    for (int i = 0; i < row; i++) {
      newVals[i] = this.values[i];
    } //for
    for (int i = (row + 1); i < (this.rowSize); i++) {
      newVals[i - 1] = this.values[i];
    } //for

    values = newVals;
    this.rowSize--;
  } // deleteRow(int)

  /**
   * Delete a column.
   *
   * @param col
   *   The number of the column to delete.
   *
   * @throws IndexOutOfBoundsException
   *   If the column is negative or greater than or equal to the width.
   */
  public void deleteCol(int col) {
    if ((col < 0) || (col >= this.colSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    Object[][] newVals = new Object[this.rowSize][this.colSize - 1];
    for (int i = 0; i < this.rowSize; i++) {
      Object[] newRow = new Object[this.colSize - 1];
      for (int j = 0; j < col; j++) {
        newRow[j] = this.values[i][j];
      } //for
      for (int j = (col + 1); j < (this.colSize); j++) {
        newRow[j - 1] = this.values[i][j];
      } //for
      newVals[i] = newRow;
    } //for

    this.values = newVals;
    this.colSize--;
  } // deleteCol(int)

  /**
   * Fill a rectangular region of the matrix.
   *
   * @param startRow
   *   The top edge / row to start with (inclusive).
   * @param startCol
   *   The left edge / column to start with (inclusive).
   * @param endRow
   *   The bottom edge / row to stop with (exclusive).
   * @param endCol
   *   The right edge / column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillRegion(int startRow, int startCol, int endRow, int endCol,
      T val) {
    if ((startRow < 0) || (startCol < 0) || (endRow < 0) || (endCol < 0)) {
      throw new IndexOutOfBoundsException();
    } else if ((startRow >= this.rowSize) || (endRow > this.rowSize)) {
      throw new IndexOutOfBoundsException();
    } else if ((startCol >= this.colSize) || (endCol > this.colSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    for (int i = startRow; i < endRow; i++) {
      for (int j = startCol; j < endCol; j++) {
        this.values[i][j] = val;
      } //for
    } //for
  } // fillRegion(int, int, int, int, T)

  /**
   * Fill a line (horizontal, vertical, diagonal).
   *
   * @param startRow
   *   The row to start with (inclusive).
   * @param startCol
   *   The column to start with (inclusive).
   * @param deltaRow
   *   How much to change the row in each step.
   * @param deltaCol
   *   How much to change the column in each step.
   * @param endRow
   *   The row to stop with (exclusive).
   * @param endCol
   *   The column to stop with (exclusive).
   * @param val
   *   The value to store.
   *
   * @throw IndexOutOfBoundsException
   *   If the rows or columns are inappropriate.
   */
  public void fillLine(int startRow, int startCol, int deltaRow, int deltaCol,
      int endRow, int endCol, T val) {
    if ((startRow < 0) || (startCol < 0) || (endRow < 0) || (endCol < 0)) {
      throw new IndexOutOfBoundsException();
    } else if ((startRow >= this.rowSize) || (endRow > this.rowSize)) {
      throw new IndexOutOfBoundsException();
    } else if ((startCol >= this.colSize) || (endCol > this.colSize)) {
      throw new IndexOutOfBoundsException();
    } //if

    int rowPosition = startRow;
    int colPosition = startCol;
    int count = 0;

    while ((rowPosition < endRow) && (colPosition < endCol)) {
      this.values[rowPosition][colPosition] = val;

      count++;
      rowPosition = (startRow + (count * deltaRow));
      colPosition = (startCol + (count * deltaCol));
    } //while
  } // fillLine(int, int, int, int, int, int, T)

  /**
   * A make a copy of the matrix. May share references (e.g., if individual
   * elements are mutable, mutating them in one matrix may affect the other
   * matrix) or may not.
   *
   * @return a copy of the matrix.
   */
  @SuppressWarnings({"unchecked", "rawtypes"})
  public Matrix clone() {
    Matrix<T> newMatrix = new MatrixV0(this.colSize, this.rowSize, this.defVal);
    for (int i = 0; i < this.rowSize; i++) {
      for (int j = 0; j < this.colSize; j++) {
        newMatrix.set(i, j, (T) this.values[i][j]);
      } //for
    } //for
    return newMatrix;
  } // clone()

  /**
   * Determine if this object is equal to another object.
   *
   * @param other
   *   The object to compare.
   *
   * @return true if the other object is a matrix with the same width,
   * height, and equal elements; false otherwise.
   */
  @SuppressWarnings("unchecked")
  public boolean equals(Object other) {
    if (this.rowSize != ((MatrixV0<T>) other).height()) {
      return false;
    } else if (this.colSize != ((MatrixV0<T>) other).width()) {
      return false;
    } //if

    for (int i = 0; i < this.rowSize; i++) {
      for (int j = 0; j < this.colSize; j++) {
        if (!(this.values[i][j].equals(((MatrixV0<T>) other).get(i, j)))) {
          return false;
        } //if
      } //for
    } //for

    return true;
  } // equals(Object)

  /**
   * Compute a hash code for this matrix. Included because any object
   * that implements `equals` is expected to implement `hashCode` and
   * ensure that the hash codes for two equal objects are the same.
   *
   * @return the hash code.
   */
  public int hashCode() {
    int multiplier = 7;
    int code = this.width() + multiplier * this.height();
    for (int row = 0; row < this.height(); row++) {
      for (int col = 0; col < this.width(); col++) {
        T val = this.get(row, col);
        if (val != null) {
          // It's okay if the following computation overflows, since
          // it will overflow uniformly.
          code = code * multiplier + val.hashCode();
        } // if
      } // for col
    } // for row
    return code;
  } // hashCode()
} // class MatrixV0
