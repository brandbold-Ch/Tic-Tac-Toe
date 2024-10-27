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
        int i = 0;
        for (int j=2; j >= 0; j--) {
            System.out.println(array[i][j]);
            i++;
        }
         */
        Class<?> obj = Matrix.class;
        Method[] methods = obj.getDeclaredMethods();

        for (Method method : methods) {
            System.out.println(method.getReturnType() == String.class);
        }
    }
}
