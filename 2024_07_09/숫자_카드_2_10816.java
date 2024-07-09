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

        for (int i = 0; i < M; i++) {
            sb.append(getCount(integerArr[i])).append(' ');
        }

        bw.write(sb.toString());
        bw.close();
        bf.close();
    }

    private static int getCount(int n) {
        int start = 0;
        int end = N - 1;
        int key = -1;
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (n == own[mid]) {
                key = mid;
                break;
            } else if (n < own[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (key == -1) {
            return 0;
        }

        int left;
        int tmp = key;
        while (tmp > 0 && own[tmp] == n) {
            tmp--;
        }
        if (tmp == 0) {
            left = 0;
        } else {
            left = tmp + 1;
        }

        int right;
        tmp = key;
        while (tmp < N && own[tmp] == n) {
            tmp++;
        }
        right = tmp - 1;

        return right - left + 1;
    }
}
/**
 * 10
 * 6 3 2 10 10 10 -10 -10 7 3
 * 8
 * 10 9 -5 2 3 4 5 -10
 */