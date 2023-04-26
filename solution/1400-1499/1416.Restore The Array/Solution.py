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
