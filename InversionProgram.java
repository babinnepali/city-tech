import java.util.Arrays;

public class InversionProgram {
	// creating method to count array of inversion
    public static int mergeAndCountApproach(
    	int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid, k = left, inversionCount = 0;
        
        //dividing into chuncks from the mid
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
    
    // implementing created method
    public static int mergeSortAndCountApproach(
    	int[] arr, int[] temp, int left, int right) {
        int mid, inversionCount = 0;
        if (left < right) {
            mid = (left + right) / 2;
            inversionCount += mergeSortAndCountApproach(arr, temp, left, mid);
            inversionCount += mergeSortAndCountApproach(arr, temp, mid + 1, right);
            inversionCount += mergeAndCountApproach(arr, temp, left, mid + 1, right);
        }
        return inversionCount;
    }
    // creating count method 
    public static int countInversions(int[] arr) {
        int[] temp = Arrays.copyOf(arr, arr.length);
        return mergeSortAndCountApproach(arr, temp, 0, arr.length - 1);
    }
    
    // implementing count method method which executes the mergeSortAndCountApproach Method
    public static void main(String[] args) {
        int[] arr = {4, 0, 1,6, 5};
        System.out.println("Total inversions: " + countInversions(arr));
    }
}
