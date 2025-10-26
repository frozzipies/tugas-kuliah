// factorial.c — kalkulator faktorial rekursif + opsi jejak rekursi
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <errno.h>

#define MAX_N 20  // 20! masih muat di unsigned long long

static unsigned long long faktorial_trace(unsigned int n, int depth, int trace) {
    if (trace) {
        for (int i = 0; i < depth; ++i) printf("  ");
        printf("f(%u)", n);
    }

    if (n <= 1) {
        if (trace) printf(" = 1  (base case)\n");
        return 1ULL;
    }

    if (trace) printf(" = %u * f(%u)\n", n, n - 1);

    unsigned long long sub = faktorial_trace(n - 1, depth + 1, trace);
    unsigned long long res = (unsigned long long)n * sub;

    if (trace) {
        for (int i = 0; i < depth; ++i) printf("  ");
        printf("→ return %u * %llu = %llu\n", n, sub, res);
    }
    return res;
}

int main(void) {
    char buf[128];

    printf("Masukkan n (0..%d): ", MAX_N);
    if (!fgets(buf, sizeof(buf), stdin)) {
        fprintf(stderr, "Gagal membaca input.\n");
        return 1;
    }

    // Trim spasi
    char *p = buf;
    while (isspace((unsigned char)*p)) p++;
    char *end = p + strlen(p);
    while (end > p && isspace((unsigned char)end[-1])) end--;
    *end = '\0';

    // Cek negatif manual
    if (*p == '-') {
        fprintf(stderr, "n tidak boleh negatif.\n");
        return 1;
    }

    errno = 0;
    char *q;
    unsigned long long n = strtoull(p, &q, 10);
    while (*q && isspace((unsigned char)*q)) q++; // abaikan spasi sisa
    if (errno != 0 || *q != '\0') {
        fprintf(stderr, "Input bukan bilangan bulat yang valid.\n");
        return 1;
    }

    if (n > MAX_N) {
        fprintf(stderr, "Batas: n <= %d (untuk mencegah overflow 64-bit).\n", MAX_N);
        return 1;
    }

    // Tanya apakah mau tampilkan jejak rekursi
    int trace = 0;
    printf("Tampilkan jejak rekursi? (y/n): ");
    if (fgets(buf, sizeof(buf), stdin)) {
        if (buf[0] == 'y' || buf[0] == 'Y') trace = 1;
    }

    unsigned long long hasil = faktorial_trace((unsigned int)n, 0, trace);
    printf("\n%llu! = %llu\n", n, hasil);
    return 0;
}
