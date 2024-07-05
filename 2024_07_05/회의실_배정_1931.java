package backJoon;

import java.io.*;
import java.util.*;

public class 회의실_배정_1931 {
    static int N;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        board = new int[N][2];
        for (int i = 0; i< N; i++) {
            st = new StringTokenizer(bf.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(board, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int endTime = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            if (endTime <= board[i][0]) {
                endTime = board[i][1];
                answer++;
            }
        }

        bw.write(new StringBuilder().append(answer).toString());
        bw.close();
        bf.close();
    }
}
