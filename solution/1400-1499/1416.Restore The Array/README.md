# [1416. 恢复数组](https://leetcode.cn/problems/restore-the-array)

[English Version](/solution/1400-1499/1416.Restore%20The%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>某个程序本来应该输出一个整数数组。但是这个程序忘记输出空格了以致输出了一个数字字符串，我们所知道的信息只有：数组中所有整数都在 <code>[1, k]</code>&nbsp;之间，且数组中的数字都没有前导 0 。</p>

<p>给你字符串&nbsp;<code>s</code>&nbsp;和整数&nbsp;<code>k</code>&nbsp;。可能会有多种不同的数组恢复结果。</p>

<p>按照上述程序，请你返回所有可能输出字符串&nbsp;<code>s</code>&nbsp;的数组方案数。</p>

<p>由于数组方案数可能会很大，请你返回它对&nbsp;<code>10^9 + 7</code>&nbsp;<strong>取余</strong>&nbsp;后的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;1000&quot;, k = 10000
<strong>输出：</strong>1
<strong>解释：</strong>唯一一种可能的数组方案是 [1000]
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;1000&quot;, k = 10
<strong>输出：</strong>0
<strong>解释：</strong>不存在任何数组方案满足所有整数都 &gt;= 1 且 &lt;= 10 同时输出结果为 s 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1317&quot;, k = 2000
<strong>输出：</strong>8
<strong>解释：</strong>可行的数组方案为 [1317]，[131,7]，[13,17]，[1,317]，[13,1,7]，[1,31,7]，[1,3,17]，[1,3,1,7]
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>s = &quot;2020&quot;, k = 30
<strong>输出：</strong>1
<strong>解释：</strong>唯一可能的数组方案是 [20,20] 。 [2020] 不是可行的数组方案，原因是 2020 &gt; 30 。 [2,020] 也不是可行的数组方案，因为 020 含有前导 0 。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>s = &quot;1234567890&quot;, k = 90
<strong>输出：</strong>34
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10^5</code>.</li>
	<li><code>s</code>&nbsp;只包含数字且不包含前导 0 。</li>
	<li><code>1 &lt;= k &lt;= 10^9</code>.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i)$，表示从字符串的第 $i$ 个字符开始恢复数组的方案数。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的执行逻辑如下：

-   如果 $i$ 超出了字符串的长度，说明我们已经恢复了整个数组，方案数为 $1$。
-   如果 $i$ 对应的字符为 $0$，那么无法恢复出任何整数，方案数为 $0$。
-   否则，我们枚举从 $i$ 开始的连续若干个字符 $s[i..j]$，将其解析成一个整数 $x$，如果 $x$ 不超过 $k$，那么我们就可以从 $j+1$ 开始继续恢复数组，方案数为 $dfs(j+1)$。我们对所有的 $x$ 的方案数求和，即为 $dfs(i)$ 的值。

最后返回 $dfs(0)$ 即可。

为了避免重复计算，我们可以使用记忆化的方法进行优化。我们用一个数组 $f$ 记录所有的 $dfs(i)$ 的值，当我们需要计算 $dfs(i)$ 时，如果 $f[i]$ 已经被计算出来了，那么我们直接返回 $f[i]$ 即可。

时间复杂度 $(n \times \log k)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度，而 $k$ 是题目给定的整数 $k$。

**方法二：动态规划**

我们也可以使用动态规划的方法计算答案。

我们定义 $f[i]$ 表示将字符串 $s$ 的前 $i$ 个字符恢复成一个整数的方案数。初始时 $f[0]=1$，答案为 $f[n]$。

考虑 $f[i]$，其中 $i \in [1,n]$。我们可以从大到小枚举 $j$，其中 $j \in [1,i]$，如果 $s[j-1..i-1]$ 对应的整数 $x$ 不超过 $k$，且 $s[j-1] \neq '0'$，那么我们就可以用 $f[j-1]$ 来更新 $f[i]$，即 $f[i] = f[i] + f[j-1]$。在计算出所有的 $f[i]$ 之后，答案即为 $f[n]$。

时间复杂度 $O(n \times \log k)$，空间复杂度 $O(n)$。其中 $n$ 是字符串的长度，而 $k$ 是题目给定的整数 $k$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        @cache
        def dfs(i: int):
            if i >= n:
                return 1
            if s[i] == '0':
                return 0
            ans = x = 0
            for j in range(i, n):
                x = x * 10 + int(s[j])
                if x > k:
                    break
                ans = (ans + dfs(j + 1)) % mod
            return ans

        n = len(s)
        mod = 10**9 + 7
        return dfs(0)
```

```python
class Solution:
    def numberOfArrays(self, s: str, k: int) -> int:
        n = len(s)
        f = [1] + [0] * n
        mod = 10**9 + 7
        for i in range(1, n + 1):
            x, p = 0, 1
            for j in range(i, max(0, i - 11), -1):
                x = int(s[j - 1]) * p + x
                if x > k:
                    break
                if s[j - 1] != '0':
                    f[i] = (f[i] + f[j - 1]) % mod
                p *= 10
        return f[n]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private Integer[] f;
    private String s;
    private int n;
    private int k;

    public int numberOfArrays(String s, int k) {
        n = s.length();
        this.s = s;
        this.k = k;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n) {
            return 1;
        }
        if (s.charAt(i) == '0') {
            return 0;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = 0;
        long x = 0;
        final int mod = (int) 1e9 + 7;
        for (int j = i; j < n; ++j) {
            x = x * 10 + s.charAt(j) - '0';
            if (x > k) {
                break;
            }
            ans = (ans + dfs(j + 1)) % mod;
        }
        return f[i] = ans;
    }
}
```

```java
class Solution {
    public int numberOfArrays(String s, int k) {
        int n = s.length();
        int[] f = new int[n + 1];
        f[0] = 1;
        final int mod = (int) 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long x = 0, p = 1;
            for (int j = i; j > Math.max(i - 11, 0); --j) {
                x = (s.charAt(j - 1) - '0') * p + x;
                if (x > k) {
                    break;
                }
                if (s.charAt(j - 1) != '0') {
                    f[i] = (f[i] + f[j - 1]) % mod;
                }
                p *= 10;
            }
        }
        return f[n];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfArrays(string s, int k) {
        int n = s.size();
        int f[n];
        memset(f, -1, sizeof(f));
        const int mod = 1e9 + 7;
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n) {
                return 1;
            }
            if (s[i] == '0') {
                return 0;
            }
            if (f[i] != -1) {
                return f[i];
            }
            int ans = 0;
            long long x = 0;
            for (int j = i; j < n; ++j) {
                x = x * 10 + s[j] - '0';
                if (x > k) {
                    break;
                }
                ans = (ans + dfs(j + 1)) % mod;
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};
```

```cpp
class Solution {
public:
    int numberOfArrays(string s, int k) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        const int mod = 1e9 + 7;
        for (int i = 1; i <= n; ++i) {
            long x = 0, p = 1;
            for (int j = i; j > max(i - 11, 0); --j) {
                x = (s[j - 1] - '0') * p + x;
                if (x > k) {
                    break;
                }
                if (s[j - 1] != '0') {
                    f[i] = (f[i] + f[j - 1]) % mod;
                }
                p *= 10;
            }
        }
        return f[n];
    }
};
```

### **Go**

```go
func numberOfArrays(s string, k int) int {
	n := len(s)
	f := make([]int, n)
	for i := range f {
		f[i] = -1
	}
	const mod int = 1e9 + 7
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n {
			return 1
		}
		if s[i] == '0' {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans, x := 0, 0
		for j := i; j < n; j++ {
			x = x*10 + int(s[j]-'0')
			if x > k {
				break
			}
			ans = (ans + dfs(j+1)) % mod
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

```go
func numberOfArrays(s string, k int) int {
	n := len(s)
	f := make([]int, n+1)
	f[0] = 1
	const mod int = 1e9 + 7
	for i := 1; i <= n; i++ {
		x, p := 0, 1
		for j := i; j > i-11 && j > 0; j-- {
			x = int(s[j-1]-'0')*p + x
			if x > k {
				break
			}
			if s[j-1] != '0' {
				f[i] = (f[i] + f[j-1]) % mod
			}
			p *= 10
		}
	}
	return f[n]
}
```

### **TypeScript**

```ts
function numberOfArrays(s: string, k: number): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(-1);
    const dfs = (i: number): number => {
        if (i >= n) {
            return 1;
        }
        if (s[i] === '0') {
            return 0;
        }
        if (f[i] !== -1) {
            return f[i];
        }
        let x = 0;
        let ans = 0;
        for (let j = i; j < n; ++j) {
            x = x * 10 + s[j].charCodeAt(0) - '0'.charCodeAt(0);
            if (x > k) {
                break;
            }
            ans += dfs(j + 1);
            ans %= 1000000007;
        }
        return (f[i] = ans);
    };
    return dfs(0);
}
```

```ts
function numberOfArrays(s: string, k: number): number {
    const n = s.length;
    const f: number[] = new Array(n + 1).fill(0);
    f[0] = 1;
    for (let i = 1; i <= n; ++i) {
        let x = 0;
        let p = 1;
        for (let j = i; j > Math.max(0, i - 11); --j) {
            x = (s[j - 1].charCodeAt(0) - '0'.charCodeAt(0)) * p + x;
            if (x > k) {
                break;
            }
            if (s[j - 1] !== '0') {
                f[i] += f[j - 1];
                f[i] %= 1000000007;
            }
            p *= 10;
        }
    }
    return f[n];
}
```

### **...**

```

```

<!-- tabs:end -->
