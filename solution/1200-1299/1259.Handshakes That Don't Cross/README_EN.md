# [1259. Handshakes That Don't Cross](https://leetcode.com/problems/handshakes-that-dont-cross)

[中文文档](/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/README.md)

## Description

<p>You are given an <strong>even</strong> number of people <code>numPeople</code> that stand around a circle and each person shakes hands with someone else so that there are <code>numPeople / 2</code> handshakes total.</p>

<p>Return <em>the number of ways these handshakes could occur such that none of the handshakes cross</em>.</p>

<p>Since the answer could be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_2.png" style="width: 450px; height: 215px;" />
<pre>
<strong>Input:</strong> numPeople = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are two ways to do it, the first way is [(1,2),(3,4)] and the second one is [(2,3),(4,1)].
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1259.Handshakes%20That%20Don%27t%20Cross/images/5125_example_3.png" style="width: 335px; height: 500px;" />
<pre>
<strong>Input:</strong> numPeople = 6
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= numPeople &lt;= 1000</code></li>
	<li><code>numPeople</code> is even.</li>
</ul>

## Solutions

**Solution 1: Dynamic Programming**

We define $f[i]$ as the number of handshake schemes for $i$ people, and $f[0]=1$ at the beginning, the answer is $f[numPeople]$.

Considering $f[i]$, where $i \in [2,4,6,\cdots,numPeople]$, we can enumerate the number of people skipped by the first person in the clockwise direction, that is, the first person skipped $j$ people to shake hands with another person, where the number of people skipped $j$ must be even, so $j \in [0,2,4,\cdots,i-2]$, then the handshake scheme of the skipped people is $f[j]$, and the remaining number of people is $k=i-j-2$, the handshake scheme is $f[k]$, multiply these two parts and add them to $f[i]$, the state transition equation is:

$$
f[i] = \sum_{j=0}^{i-2} f[j] \times f[i-j-2]
$$

where $i \in [2,4,6,\cdots,numPeople]$, and $j$ is even, and $j \in [0,2,4,\cdots,i-2]$.

Finally, the answer is $f[numPeople]$.

The time complexity is $O(n^2)$, and the space complexity is $O(n)$. Where $n$ is the total number of people.

<!-- tabs:start -->

### **Python3**

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
