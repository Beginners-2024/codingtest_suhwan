package backJoon;

import java.io.*;
import java.util.*;

public class 동전_0_11047 {
    static int N;
    static int K;
    // 동전 지갑
    static List<Integer> wallet;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        wallet = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            wallet.add(Integer.parseInt(st.nextToken()));
        }

        // 동전의 가격이 높은 순으로 내림차순 정렬
        wallet.sort((o1, o2) -> o2 - o1);

        int answer = 0;

        //가격보다 값이 작고, 그중 가장 큰 동전을 최대로 사용
        for (Integer coin : wallet) {
            if (K == 0) {
                break;
            }
            if (coin > K) {
                continue;
            }
            while (K >= coin) {
                K -= coin;
                answer++;
            }
        }

        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        bf.close();
    }
}
