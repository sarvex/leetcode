function numberOfWays(numPeople: number): number {
    const mod = BigInt(10 ** 9 + 7);
    const f: bigint[] = new Array(numPeople + 1).fill(0n);
    f[0] = 1n;
    for (let i = 2; i <= numPeople; i += 2) {
        for (let j = 0; j < i - 1; j += 2) {
            const k = i - j - 2;
            f[i] = (f[i] + f[j] * f[k]) % mod;
        }
    }
    return Number(f[numPeople]);
}
