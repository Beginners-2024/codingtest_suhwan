package backJoon;

import java.io.*;
import java.util.*;

public class 좌표_압축_18870 {
    static int N;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(st.nextToken());

        board = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            board[i] = Integer.parseInt(st.nextToken());
        }
        int[] tmp = new int[N];
        System.arraycopy(board, 0, tmp, 0, N);
        Arrays.sort(board);

        /**
         * 정렬한 배열값을 순서대로 rank를 매겨준다.
         * 만약 중복된다면, 넘어간다.
         * 처음 들어온 순서대로, key, value 값으로 순위를 출력한다.
         */
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 0;
        for (int n : board) {
            if (!map.containsKey(n)){
                map.put(n, rank);
                rank++;
            }
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            sb.append(map.get(tmp[i])).append(' ');
        }
        bw.write(sb.toString());
        bw.close();
        bf.close();
    }
}
