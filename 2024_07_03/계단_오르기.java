package backJun;

import java.io.*;
import java.util.*;

public class 계단_오르기 {
    static int[] dp;
    static int N;
    static int[] stair;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        dp = new int[301];
        stair = new int[301];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(bf.readLine());
            stair[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = stair[1];
        dp[2] = dp[1] + stair[2];
        /**
         * 계단 오르는 방식은 연속적으로 3개의 계단을 오를수 없다는게 포인트!
         *
         * 마지막 계단 N에 올수 있는 경우의 수
         * dp[n - 1] + stair[n] : 한칸만을 올라온 경우
         * stair[n - 1] + stair[n] + dp[n - 3] : 두칸을 올라온 경우 -> dp[n -3]을 더함으로써, 3칸을 연속적으로 밟고 올라오는 경우를 예외처리 가능
         */
        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + stair[i], dp[i - 3] + stair[i] + stair[i - 1]);
        }
        bw.write(new StringBuilder().append(dp[N]).toString());
        bw.close();
        bf.close();
    }
}
