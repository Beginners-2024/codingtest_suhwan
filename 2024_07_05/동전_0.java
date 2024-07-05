package backJoon;

import java.io.*;
import java.util.*;

public class 동전_0 {
    static int N;
    static int K;
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

        wallet.sort((o1, o2) -> o2 - o1);

        int answer = 0;

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
