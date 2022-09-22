import java.util.Arrays;

public class Lab7_MatrixMul {
    public static void main(String[] args) {
        int[][] inputA = { { 5, 6, 7 }, { 4, 8, 9 } };
        int[][] inputB = { { 6, 4 }, { 5, 7 }, { 1, 1 } };
        MyData matA = new MyData(inputA);
        MyData matB = new MyData(inputB);
        int matC_r = matA.data.length;
        int matC_c = matB.data[0].length;
        MyData matC = new MyData(matC_r, matC_c);
        matC.show();

        MatrixMulThread runner=new MatrixMulThread(matC_r, matC_c, matA, matB, matC);
        Thread thrd=new Thread(runner);
        thrd.start();
        
        try {
            thrd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

}

class MatrixMulThread implements Runnable {
    int processing_row; int processing_col;
    MyData datA; MyData datB; MyData datC;
    MatrixMulThread(int tRow, int tCol,MyData a, MyData b, MyData c) {
        this.datA=a;
        this.datB=b;
        this.datC=c;
        for(int i=0;i<tRow;++i){
            for(int j=0;j<tCol;++j){
                for(int k=0;k<tRow;++k){
                    this.datC.data[i][j]+=(this.datC.data[i][k]*this.datB.data[k][j]);
                    
                }
            }
        }
    }
    
    public void run() {
        this.datC.show();
    }
}

class MyData {
    int[][] data;
    MyData(int[][] m) {
         data = m; 
    }

    MyData(int r, int c) {
        data = new int[r][c];
        for (int[] aRow : data)
            Arrays.fill(aRow, 9);
    }
    public void show() {
        System.out.println(Arrays.deepToString(data));
    }
} 