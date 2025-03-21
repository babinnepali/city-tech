import java.util.Arrays;

public class InversionProgram {
    public static int mergeAndCountApproach(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid, k = left, inversionCount = 0;

        while (i <= mid - 1 && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversionCount += (mid - i); 
            }
        }

        while (i <= mid - 1) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, left, arr, left, right - left + 1);

        return inversionCount;
    }

    public static int mergeSortAndCountApproach(int[] arr, int[] temp, int left, int right) {
        int mid, inversionCount = 0;
        if (left < right) {
            mid = (left + right) / 2;
            inversionCount += mergeSortAndCountApproach(arr, temp, left, mid);
            inversionCount += mergeSortAndCountApproach(arr, temp, mid + 1, right);
            inversionCount += mergeAndCountApproach(arr, temp, left, mid + 1, right);
        }
        return inversionCount;
    }

    public static int countInversions(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        return mergeSortAndCountApproach(arr, temp, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] arr = {1, 20, 6, 4, 5};
        System.out.println("Total inversions: " + countInversions(arr));
    }
}
