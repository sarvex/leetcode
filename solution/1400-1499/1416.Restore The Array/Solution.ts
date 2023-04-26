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
