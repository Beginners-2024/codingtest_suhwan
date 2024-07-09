package backJoon;

import java.io.*;
import java.util.*;

public class 수찾기_1920 {
    static int N, M;
    static int[] naturalArr;
    static int[] integerArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        naturalArr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            naturalArr[i] = Integer.parseInt(st.nextToken());
        }
        /**
         * 이진 탐색을 사용하기 위해 오름차순 정렬한다.
         * 정렬해야, 찾는 key 값고 arr[mid] 값을 비교하여, start, end 값을 옮기며 serch 할 수 있음
         */
        Arrays.sort(naturalArr);

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        integerArr = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            integerArr[i] = Integer.parseInt(st.nextToken());
        }

        /**
         * 한번에 출력하기 위해, StringBuilder 사용
         * ex) String += "1"; 을 사용하기 보다는 StringBuilder를 사용하면 메모리, 시간적 측면으로 더 빠름
         * String 클래스가 StringBuilder 클래스보다 성능이 떨어지는 이유
         * : String 클래스의 immutable 특성 때문.
         * immutable 이란 변경할 수 없는, 불변의 라는 뜻으로 String 의 value 값은 한 번 생성되면 변경될 수 없습니다.
         * String class에서의 '+'연산을 사용하여, "a" + "bb" 을 실행했을 때,
         * 기존 "a", "bb" 참조하던 String은 쓰레기가 되어 나중에 가비지 컬렉터에 의해 처리되기 떄문
         *
         * ps. StringBuilder(단일 스레드 지원) vs StringBuffer(멀티 스레드 지원)
         * StringBuilder가 더 빠르다. cuz StringBuffer는 동기화 처리를 하기 때
         */
        StringBuilder sb = new StringBuilder();

        for (int n : integerArr) {
            sb.append(search(n)).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        bf.close();
    }

    private static int search(int n) {
        int start = 0;
        int end = N - 1;
        int mid;
        /**
         * n의 값을 찾기 위해, 시작 점과 끝 점을 옮겨 가며 순회한다.
         * 만약 start <= end 라면 값이 없다
         * 배열을 한번 순회 하면서 찾는 시간복잡도 O(N)
         * 이진 탐색 O(logn) -> 탐색 범위를 반으로 줄이면서 서치하기 떄문
         */
        while (start <= end) {
            mid = (start + end) / 2;
            if (n == naturalArr[mid]) {
                return 1;
            } else if (n < naturalArr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
    }
}
