import java.io.File;
import java.lang.reflect.Method;

public class Matrix {

    public String test() {
        return "Hello";
    }

    public static void main(String[] args) {
        byte[][] array = new byte[3][3];
        array[0][0] = 1;
        array[0][1] = 2;
        array[0][2] = 3;
        array[1][0] = 4;
        array[1][1] = 5;
        array[1][2] = 6;
        array[2][0] = 7;
        array[2][1] = 8;
        array[2][2] = 9;

        /*
        int counter = 0;
        for (int j=0; j < 3; j++) {
            for (int i=0; i < 3; i++) {
                counter += array[j][i];
            }
            System.out.println(counter);
            counter = 0;
        }
         */

        int counter = 0;
        for (int k=0; k<3; k++) {
            for (int i=0; i<3; i++) {
                for (int j=0; j<1; j++) {
                    counter += array[i][j+k];
                }
            }
            System.out.println(counter);
            counter = 0;
        }
    }
}
