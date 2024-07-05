package backJun;

import java.io.*;
import java.util.*;

public class 일_이_삼_더하기 {
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());

        /**
         * dp 문제는 점화식이나 규칙을 찾는 것이 중요하다
         * 1를 1, 2, 3으로 만드는 조합 : 1
         * 1
         *
         * 2를 1, 2, 3으로 만드는 조합 : 2
         * 1만 사용
         * 1 + 1
         *
         * 2만 사용
         * 2
         *
         * 3을 1, 2, 3으로 만드는 조합 : 4
         * 1만 사용
         * 1 + 1 + 1
         *
         * 1, 2 사용
         * 1 + 2
         * 2 + 1
         *
         * 3만 사용
         * 3
         *
         * 4를 1, 2, 3으로 만드는 조합 : 7
         * 1만 사용
         * 1 + 1 + 1 + 1
         *
         * 1, 2 사용
         * 2 + 1 + 1
         * 1 + 2 + 1
         * 1 + 1 + 2
         *
         * 1, 3 사용
         * 1 + 3
         * 3 + 1
         *
         * 2만 사용
         * 2 + 2
         * 총 7가지
         *
         * 5를 1, 2, 3으로 만드는 조합 : 13
         * 1만 사용
         * 1 + 1 + 1 + 1 + 1
         *
         * 1, 2 사용
         * 1 + 1 + 1 + 2
         * 1 + 1 + 2 + 1
         * 1 + 2 + 1 + 1
         * 2 + 1 + 1 + 1
         * 1 + 2 + 2
         * 2 + 1 + 2
         * 2 + 1 + 2
         *
         * 1, 3 사용
         * 1 + 1 + 3
         * 1 + 3 + 1
         * 3 + 1 + 1
         *
         * 2, 3 사용
         * 2 + 3
         * 3 + 2
         *
         * 점화식 결과 : dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1]
         */
        dp = new int[12];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int num = Integer.parseInt(st.nextToken());
            bw.write(new StringBuilder().append(dp[num]).append("\n").toString());
        }
        bw.close();
        bf.close();
    }
}
