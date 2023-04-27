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