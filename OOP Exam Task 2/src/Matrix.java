/*
    Написать класс матриц, поддерживающий операцию транспонирования.
    Должны быть описан один класс, который должен:
        инкапсулировать в себе массив-матрицу;
        иметь конструктор, параметрами которого являются размеры матрицы;
        реализовывать получение размеров матрицы;
        реализовывать получение и изменение отдельных элементов матрицы;
        иметь статический метод транспонирования матриц, возвращающий транспонированную матрицу.
 */

public class Matrix
{
    private int arr[][];

    public Matrix(int rows, int columns)
    {
        arr = new int[rows][columns];
    }

    public int getRows()
    {
        return arr.length;
    }

    public int getColumns()
    {
        return arr.length >= 1 ? arr[0].length : 0;
    }

    public int getItem(int row, int column)
    {
        return (arr.length >= row && arr.length >= 1 && arr[0].length >= column) ? arr[row][column] : 0;
    }

    public void setItem(int row, int column, int num)
    {
        if (arr.length >= row && arr.length >= 1 && arr[0].length >= column) arr[row][column] = num;
    }

    public static Matrix transpose(Matrix matrix)
    {
        Matrix result = new Matrix(matrix.getRows(), matrix.getColumns());

        for (int i = 0; i < result.getRows(); ++i)
        {
            for (int j = 0; j < result.getColumns(); ++j)
            {
                result.setItem(j, i, matrix.getItem(i, j));
            }
        }
        return result;
    }
}
