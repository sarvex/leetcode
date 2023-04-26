# [1416. Restore The Array](https://leetcode.com/problems/restore-the-array)

[中文文档](/solution/1400-1499/1416.Restore%20The%20Array/README.md)

## Description

<p>A program was supposed to print an array of integers. The program forgot to print whitespaces and the array is printed as a string of digits <code>s</code> and all we know is that all integers in the array were in the range <code>[1, k]</code> and there are no leading zeros in the array.</p>

<p>Given the string <code>s</code> and the integer <code>k</code>, return <em>the number of the possible arrays that can be printed as </em><code>s</code><em> using the mentioned program</em>. Since the answer may be very large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1000&quot;, k = 10000
<strong>Output:</strong> 1
<strong>Explanation:</strong> The only possible array is [1000]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1000&quot;, k = 10
<strong>Output:</strong> 0
<strong>Explanation:</strong> There cannot be an array that was printed this way and has all integer &gt;= 1 and &lt;= 10.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;1317&quot;, k = 2000
<strong>Output:</strong> 8
<strong>Explanation:</strong> Possible arrays are [1317],[131,7],[13,17],[1,317],[13,1,7],[1,31,7],[1,3,17],[1,3,1,7]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists of only digits and does not contain leading zeros.</li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
