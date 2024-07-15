import java.util.*;

public class RemoveDuplicates {
    public static int[] removeDuplicates(int[] nums) {
        Set<Integer> uniqueSet = new HashSet<>();
        
        for (int num : nums) {
            uniqueSet.add(num); // Set에 추가 (중복 자동 제거)
        }

        // Set을 배열로 변환
        int[] result = new int[uniqueSet.size()];
        int index = 0;
        for (int num : uniqueSet) {
            result[index++] = num;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {4, 2, 2, 3, 4, 1, 3};
        int[] uniqueNums = removeDuplicates(nums);
        System.out.println(Arrays.toString(uniqueNums)); // [1, 2, 3, 4]
    }
}
