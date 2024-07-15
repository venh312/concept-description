/*
※ 시간 복잡도
1. 문자 배열 및 StringBuilder 방법: O(n) (n은 문자열 길이)
2. 재귀 방법: O(n) (하지만 추가적인 스택 메모리 사용)
3. Stream 방법: O(n) (Stream 생성과 변환에 소요되는 시간 포함)

가장 효율적이고 간단한 방법은 문자 배열 또는 StringBuilder
*/
public class ReverseString {
    // 1. 문자 배열 사용
    public static String reverse(String s) {
        char[] charArray = s.toCharArray(); // 문자열을 문자 배열로 변환
        int left = 0;
        int right = charArray.length - 1;

        // 두 포인터를 이용하여 문자열 뒤집기
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return new String(charArray); // 문자 배열을 다시 문자열로 변환
    }

    // 2. StringBuilder 사용
    public static String reverse2(String s) {
        StringBuilder sb = new StringBuilder(s); // 문자열을 StringBuilder로 변환
        return sb.reverse().toString(); // 뒤집고 문자열로 변환
    }

    // 3. 재귀 사용
    public static String reverse(String s) {
        if (s.isEmpty()) {
            return s; // 기본 조건: 빈 문자열
        }
        return s.charAt(s.length() - 1) + reverse(s.substring(0, s.length() - 1)); // 재귀 호출
    }

    // 4. Stream 사용 (Java 8 이상)
    public static String reverse(String s) {
        return new StringBuilder(s)
                .reverse()
                .toString(); // StringBuilder 사용
    }

    public static void main(String[] args) {
        String s = "hello";
        String reversed = reverse(s);
        System.out.println(reversed); // "olleh"
    }
}
