public class Main {
    public static void main(String[] args) {
        int[] array = {3,5,6,7,8,9};
        System.out.println(getMaxElements(array));
    }
    public static int[] getMaxElements(int[] array) {
        int temp = array[0];
        int[] newArray = new int[3];
        int first = 0, second = 0, third = 0;

        for (int i = 0; i < array.length; i++) {
            if (temp < array[i]) {
                first = array[i];
                if (first < array[i]) {
                    second = array[i];
                    if (second < array[i]) {
                        third = array[i];
                    }
                }
            }
        }
        newArray[0] = first;
        newArray[1] = second;
        newArray[2] = third;
        return newArray;
    }
}