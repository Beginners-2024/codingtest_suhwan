## 수찾기_1920
### 소요시간 
15분

### 간단 풀이 방법
- n의 값을 찾기 위해, 인덱스의 시작 점과 끝 점을 옮겨 가며 순회한다.
- 만약 start <= end 라면 값이 없다 생각하고 순회 종료
- 배열을 한번 순회 하면서 찾는 시간복잡도 O(N)
- 이진 탐색 O(logn) -> 탐색 범위를 반으로 줄이면서 서치하기 떄문

### pseudo code
```java
while (start <= end) {
            mid = (start + end) / 2;
            if (n == naturalArr[mid]) {
                return 1;
            } else if (n < naturalArr[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return 0;
```

### 메모리 및 시간
- 42420kb
- 628ms

## 숫자_카드_2_10816
### 소요시간
1시간
**runtime error** 해결하느라 좀 걸림

### 간단 풀이 방법
처음에는 n 의 index를 찾아서 while문을 돌면서 첫번째 n의 시작 인덱스와 끝 인덱스를 구하는 방식으로 풀이
하지만, 시간초과가 나왔다. ex) [1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1.........,1,1,1,1,1] 중간 값이 1이면 0, 배열 길이까지
while을 순회해야하기 때문....
해결책 :
이진 탐색을 하면서, 시작 인덱스와 끝 인덱스를 각각 구하는 method를 만들기로 함
같은 이진 탐색이지만, 시작과 끝을 판단할 수 있는 방법을 갈구하게 됌;;
해답은 arr[mid] 값에 대한 판단을 다르게 하는 것이다.
lower index를 찾을때는, key <= arr[mid] 라면 end를 mid 값으로 계속 옮긴다.
 작거나 같을 경우 계속해서 찾는 것 : [1, 3, 3, 3, 3, 10]
 1번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 6, mid = 3;
|               *       |
 2번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 3, mid = 1;
|         *             |
 3번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 1, mid = 1;
|     *                 |
 4번쩨 [1, 3, 3, 3, 3, 10] -> start = 0, end = 1, mid = 0;
|      *                | -> start = 1, end = 1, mid = 1;
Return start = 1; arr[start] = 3;

upper index를 찾을 때는, key < arr[mid] 라면 end를 mid 값으로 계속 옮긴다.
 1번째 [1, 3, 3, 3, 3, 10] -> start = 0, end = 6, mid = 3;
|               *       |
 2번째 [1, 3, 3, 3, 3, 10] -> start = 4, end = 3, mid = 3;
|                  *    |
Return start = 4; arr[start] = 3;

### pseudo code
```java
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
```

### 메모리 및 소요시간
- 127064kb
- 1460ms (자바여서 더 느린것인가....)

## 좌표_압축_18870
### 소요시간
5분

### 간단 풀이 방법
- 정렬한 배열값을 순서대로 rank를 매겨준다.
- 만약 중복된다면, 넘어간다.
- 처음 들어온 순서대로, key, value 값으로 순위를 출력한다.

### pseudo code
```java
for (int n : board) {
    if (!map.containsKey(n)){
        map.put(n, rank);
        rank++;
    }
}
```

### 메모리 및 소요시간
- 264864kb
- 1808ms
