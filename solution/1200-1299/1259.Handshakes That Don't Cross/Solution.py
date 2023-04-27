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
