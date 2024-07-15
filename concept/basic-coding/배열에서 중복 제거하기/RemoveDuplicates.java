import java.util.*;
/*
1. Set 자료구조를 사용하여 중복을 자동으로 제거합니다.
2. Set에 모든 요소를 추가한 후, 다시 배열로 변환합니다.
Set을 이용한 방법은 중복된 요소를 자동으로 제거하며, 평균 시간 복잡도가 O(n)입니다. 하지만 순서가 보장되지 않으므로, 순서가 필요한 경우 LinkedHashSet을 사용할 수 있습니다.
*/
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
