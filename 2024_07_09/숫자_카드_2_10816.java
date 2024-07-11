package backJoon;

import java.io.*;
import java.util.*;

public class 숫자_카드_2_10816 {
    static int N, M;
    static int[] own;
    static int[] integerArr;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        own = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            own[i] = Integer.parseInt(st.nextToken());
        }
        // 이진 탐색을 위한 sort
        Arrays.sort(own);

        st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        integerArr = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            integerArr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        /**
         * 처음에는 n 의 index를 찾아서 while문을 돌면서 첫번째 n의 시작 인덱스와 끝 인덱스를 구하는 방식으로 풀이
         * 하지만, 시간초과가 나왔다. ex) [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1.........,1,1,1,1,1] 중간 값이 1이면 0, 배열 길이까지
         * while을 순회해야하기 때문....
         *
         * 해결책 :
         * 이진 탐색을 하면서, 시작 인덱스와 끝 인덱스를 각각 구하는 method를 만들기로 함
         * 같은 이진 탐색이지만, 시작과 끝을 판단할 수 있는 방법을 갈구하게 됌;;
         * 해답은 arr[mid] 값에 대한 판단을 다르게 하는 것이다.
         * lower index를 찾을때는, key <= arr[mid] 라면 end를 mid 값으로 계속 옮긴다.
         *  작거나 같을 경우 계속해서 찾는 것 : [1, 3, 3, 3, 3, 10]
         *  1번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 6, mid = 3;
         * |               *       |
         *  2번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 3, mid = 1;
         * |         *             |
         *  3번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 1, mid = 1;
         * |     *                 |
         *  4번쩨 [1, 3, 3, 3, 3, 10] -> start = 0, end = 1, mid = 0;
         * |      *                | -> start = 1, end = 1, mid = 1;
         * Return start = 1; arr[start] = 3;
         *
         * upper index를 찾을 때는, key < arr[mid] 라면 end를 mid 값으로 계속 옮긴다.
         *  1번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 6, mid = 3;
         * |               *       |
         *  2번째 [1, 3, 3, 3, 3, 10] -> start = 4, end = 3, mid = 3;
         * |                  *    |
         * Return start = 4; arr[start] = 3;
         */
        for (int i = 0; i < M; i++) {
            int cnt = upperBoundary(integerArr[i]) - lowerBoundary(integerArr[i]);
            sb.append(cnt).append(' ');
        }

        bw.write(sb.toString());
        bw.close();
        bf.close();
    }

    private static int lowerBoundary(int n) {
        int start = 0;
        int end = N;

        while (start < end) {
            int mid = (start + end) / 2;

            if (n <= own[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    private static int upperBoundary(int n) {
        int start = 0;
        int end = N;

        while (start < end) {
            int mid = (start + end) / 2;

            if (n < own[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}