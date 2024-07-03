package backJun;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 일로_만들기 {
    static int[] dp;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        dp = new int[1000001];

        /**
         * dp는 점화식이 중요함
         * 2, 3은 무조건 한번의 연산으로 1을 만들 수 있다 dp[2] = 1, dp[3] = 1
         * 2의 연산 : 2 -> 1 (1번)
         * 3의 연산 : 3 -> 1 (1번)
         * 4의 연산 : 4 -> 3 -> 1 OR 4 -> 2 -> 1 (2번)
         * 5의 연산 : 5 -> 4 -> 3 -> 1 OR 5 -> 4 -> 2 -> 1 (3번 = 4의 연산 횟수 + 1);
         * 5 -> {4 -> 2 -> 1 (4의 연산 횟수)}
         * 6의 연산 : 6 -> 2 -> 1 OR 6 -> 3 -> 1 (2번);
         * 6 -> {3 -> 1 (3의 연산 횟수)}
         * 7의 연산 : 7 -> 6 -> 3 -> 1 OR 7 -> 6 -> 2 -> 1 (3번 = 6의 연산 횟수 + 1)
         *
         * 여기서 규칙을 발견할 수 있다
         * N 번째가 2나 3으로 나누어 떨어진다면, dp[N/2 OR N/3] + 1 값과 dp[i](1을 뺸 결과) 중 min 값을 선택하면 된다.
         * */
        for (int i = 2; i <= N; i++) {
            // 1을 뺀다 -> 연산이 하나 더해지기에 전의 숫자 연산 횟수 + 1
            dp[i] = dp[i - 1] + 1;

            //위의 주석 참고
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }

        StringBuilder sb = new StringBuilder();

        //BufferedWriter을 사용하면 더 빠르다는 소식덕에 사용해봤습니다.
        bw.write(sb.append(dp[N]).toString());
        bf.close();
        bw.close();
    }
}
