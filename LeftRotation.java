import java.util.Arrays;

public class LeftRotation {

    static int[] rotLeft(int[] inputArray, int numRotations) {

        int rotatedInteger = 0;
        int arraySize = inputArray.length;
        int[] rotatedArray = new int[arraySize];

        for (int i = 0; i < inputArray.length; i++) {

            rotatedArray[i] = inputArray[i];

        }

        //the left rotation loop
        //this will take the array and perform numRotations amount of left rotations

        for (int i = 0; i < numRotations; i++) {

            rotatedInteger = rotatedArray[0];

            for (int j = 0; j < arraySize - 1; j++) {
                rotatedArray[j] = rotatedArray[j+1];
            }
            rotatedArray[arraySize - 1] = rotatedInteger;
        }
        return rotatedArray;
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 4, 5};

        System.out.println(Arrays.toString(rotLeft(array, 4)));

    }

}
