# [1259. 不相交的握手](https://leetcode.cn/problems/handshakes-that-dont-cross)

[English Version](/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>偶数</strong>&nbsp;个人站成一个圆，总人数为&nbsp;<code>num_people</code>&nbsp;。每个人与除自己外的一个人握手，所以总共会有&nbsp;<code>num_people / 2</code>&nbsp;次握手。</p>

<p>将握手的人之间连线，请你返回连线不会相交的握手方案数。</p>

<p>由于结果可能会很大，请你返回答案 <strong>模</strong>&nbsp;<strong><code>10^9+7</code></strong>&nbsp;后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num_people = 2
<strong>输出：</strong>1
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_2.png" style="height: 311px; width: 651px;"></p>

<pre><strong>输入：</strong>num_people = 4
<strong>输出：</strong>2
<strong>解释：</strong>总共有两种方案，第一种方案是 [(1,2),(3,4)] ，第二种方案是 [(2,3),(4,1)] 。
</pre>

<p><strong>示例 3：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_3.png" style="height: 992px; width: 664px;"></p>

<pre><strong>输入：</strong>num_people = 6
<strong>输出：</strong>5
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>num_people = 8
<strong>输出：</strong>14
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= num_people &lt;= 1000</code></li>
	<li><code>num_people % 2 == 0</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示 $i$ 个人的握手方案数，初始时 $f[0]=1$，答案为 $f[numPeople]$。

考虑 $f[i]$，其中 $i \in [2,4,6,\cdots,numPeople]$，我们可以在顺时针方向上枚举第一个人跳过的人数 $j$，即第一个人跳过了 $j$ 个人去与另一个人握手，这里跳过的人数 $j$ 必须是偶数，所以 $j \in [0,2,4,\cdots,i-2]$，那么跳过的人的握手方案数为 $f[j]$，剩下的人数为 $k=i-j-2$，握手方案数为 $f[k]$，将这两部分相乘，再累加到 $f[i]$ 上即可，状态转移方程为：

$$
f[i] = \sum_{j=0}^{i-2} f[j] \times f[i-j-2]
$$

其中 $i \in [2,4,6,\cdots,numPeople]$，而 $j$ 为偶数，且 $j \in [0,2,4,\cdots,i-2]$。

最后答案即为 $f[numPeople]$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为总人数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfWays(self, numPeople: int) -> int:
        mod = 10**9 + 7
        f = [0] * (numPeople + 1)
        f[0] = 1
        for i in range(2, numPeople + 1, 2):
            for j in range(0, i - 1, 2):
                k = i - j - 2
                f[i] = (f[i] + f[j] * f[k]) % mod
        return f[numPeople]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int numberOfWays(int numPeople) {
        final int mod = (int) 1e9 + 7;
        long[] f = new long[numPeople + 1];
        f[0] = 1;
        for (int i = 2; i <= numPeople; i += 2) {
            for (int j = 0; j < i - 1; j += 2) {
                int k = i - j - 2;
                f[i] = (f[i] + f[j] * f[k]) % mod;
            }
        }
        return (int) f[numPeople];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfWays(int numPeople) {
        const int mod = 1e9 + 7;
        long long f[numPeople + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 2; i <= numPeople; i += 2) {
            for (int j = 0; j < i - 1; j += 2) {
                int k = i - j - 2;
                f[i] = (f[i] + f[j] * f[k]) % mod;
            }
        }
        return f[numPeople];
    }
};
```

### **Go**

```go
func numberOfWays(numPeople int) int {
	const mod int = 1e9 + 7
	f := make([]int, numPeople+1)
	f[0] = 1
	for i := 2; i <= numPeople; i += 2 {
		for j := 0; j < i-1; j += 2 {
			k := i - j - 2
			f[i] = (f[i] + f[j]*f[k]) % mod
		}
	}
	return f[numPeople]
}
```

### **TypeScript**

```ts
function numberOfWays(numPeople: number): number {
    const mod = BigInt(10 ** 9 + 7);
    const f: bigint[] = new Array(numPeople + 1).fill(0n);
    f[0] = 1n;
    for (let i = 2; i <= numPeople; i += 2) {
        for (let j = 0; j < i - 1; j += 2) {
            const k = i - j - 2;
            f[i] = (f[i] + f[j] * f[k]) % mod;
        }
    }
    return Number(f[numPeople]);
}
```

### **...**

```

```

<!-- tabs:end -->
