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