class Solution {
public:
    int numberOfWays(int numPeople) {
        const int mod = 1e9 + 7;
        long long f[numPeople + 1];
        memset(f, 0, sizeof(f));
        f[0] = 1;
        for (int i = 2; i <= numPeople; i += 2) {
            for (int j = 0; j < i - 1; j += 2) {
                int k = i - j - 2;
                f[i] = (f[i] + f[j] * f[k]) % mod;
            }
        }
        return f[numPeople];
    }
};