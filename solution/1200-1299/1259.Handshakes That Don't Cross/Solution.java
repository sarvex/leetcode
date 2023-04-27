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