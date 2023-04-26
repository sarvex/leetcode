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