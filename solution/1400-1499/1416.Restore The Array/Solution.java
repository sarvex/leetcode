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