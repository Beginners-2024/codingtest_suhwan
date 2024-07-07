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

        //회의실의 끝나는 시간이 빠른 순으로 정렬, 만약 같다면 시작시간이 빠른 순으로 정렬
        //new comparator 사용보다는 lamda를 사용하는게 더 편함
        Arrays.sort(board, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });

        int endTime = 0;
        int answer = 0;

        // 직전 회의 끝나는 시간이 현재 회의 시작 시간보다 작다면, 회의를 진행할 수 있기 떄문에 갱신 해준다.
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
