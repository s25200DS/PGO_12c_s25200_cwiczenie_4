public final class Matrix {
    private static int column;

    private static int row;

    private static int[][] temporaryData;


    private static int currentEmptyRow;

    private int[][] data;

    private Matrix() {
    }

    public static Matrix create() {
        var matrix = new Matrix();
        matrix.data = Matrix.temporaryData;

        return matrix;
    }

    public static void setUpMatrix(int rowCount, int columnCount) throws Exception {
        if (rowCount <= 0 || columnCount <= 0) {
            throw new Exception("rowCount and columnCount values must be greater than 0");
        }

        Matrix.column = columnCount;
        Matrix.row = rowCount;
        Matrix.temporaryData = new int[column][row];
        Matrix.currentEmptyRow = 0;
    }

    public static void insertRow(int[] row) throws Exception {
        if (row.length != Matrix.temporaryData[0].length) {
            throw new Exception("Row should be the same size as rowCount");
        }

        System.arraycopy(
                row, 0, temporaryData[Matrix.currentEmptyRow],
                0, temporaryData[Matrix.currentEmptyRow].length
        );

        Matrix.currentEmptyRow++;
    }

    private static boolean isSizeEquals(Matrix matrixA, Matrix matrixB) {
        return matrixA.data.length == matrixB.data.length || matrixA.data[0].length == matrixB.data[0].length;
    }

    public Matrix add(Matrix matrixB) throws Exception {
        if (!Matrix.isSizeEquals(this, matrixB)) {
            throw new Exception("Both matrix should be the same size!");
        }

        for (var i = 0; i < this.data.length; i++) {
            for (var j = 0; j < this.data.length; j++) {
                this.data[i][j] += matrixB.data[i][j];
            }
        }

        return this;
    }


    public static Matrix add(Matrix matrixA, Matrix matrixB) throws Exception {
        if (!Matrix.isSizeEquals(matrixA, matrixB)) {
            throw new Exception("Both matrix should be the same size!");
        }

        Matrix.setUpMatrix(matrixA.data.length, matrixA.data[0].length);
        for (var i = 0; i < matrixA.data.length; i++) {
            Matrix.insertRow(new int[matrixA.data.length]);
        }

        return Matrix.create()
                .add(matrixA)
                .add(matrixB);
    }

    public Matrix subtract(Matrix matrixB) throws Exception {
        if (!Matrix.isSizeEquals(this, matrixB)) {
            throw new Exception("Both matrix should be the same size!");
        }

        for (var i = 0; i < this.data.length; i++) {
            for (var j = 0; j < this.data.length; j++) {
                this.data[i][j] -= matrixB.data[i][j];
            }
        }

        return this;
    }

    public void print() {
        for (var i = 0; i < column; i++) {
            System.out.print("|\t");
            for (var j = 0; j < row; j++) {
                System.out.printf("%d\t", data[i][j]);
            }
            System.out.println("|");
        }
    }
}