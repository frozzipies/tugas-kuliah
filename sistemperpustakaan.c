#include <stdio.h>
#include <string.h>

union Media {
    char nama_buku[50];
    char nama_majalah[50];
};

struct Koleksi {
    char judul[100];
    int tahun_terbit;
    enum { BUKU, MAJALAH } jenis;
    union Media media;
};

// Fungsi menampilkan data koleksi
void tampilkanKoleksi(struct Koleksi k) {
    printf("\n=== Data Koleksi ===\n");
    printf("Judul: %s\n", k.judul);
    printf("Tahun Terbit: %d\n", k.tahun_terbit);

    if (k.jenis == BUKU)
        printf("Jenis: Buku\nNama Buku: %s\n", k.media.nama_buku);
    else
        printf("Jenis: Majalah\nNama Majalah: %s\n", k.media.nama_majalah);
}

int main() {
    struct Koleksi koleksi1;

    printf("Masukkan judul: ");
    fgets(koleksi1.judul, sizeof(koleksi1.judul), stdin);
    koleksi1.judul[strcspn(koleksi1.judul, "\n")] = '\0';

    printf("Masukkan tahun terbit: ");
    scanf("%d", &koleksi1.tahun_terbit);
    getchar(); // membersihkan buffer

    printf("Pilih jenis media (1=Buku, 2=Majalah): ");
    int pilihan;
    scanf("%d", &pilihan);
    getchar();

    if (pilihan == 1) {
        koleksi1.jenis = BUKU;
        printf("Masukkan nama buku: ");
        fgets(koleksi1.media.nama_buku, sizeof(koleksi1.media.nama_buku), stdin);
        koleksi1.media.nama_buku[strcspn(koleksi1.media.nama_buku, "\n")] = '\0';
    } else {
        koleksi1.jenis = MAJALAH;
        printf("Masukkan nama majalah: ");
        fgets(koleksi1.media.nama_majalah, sizeof(koleksi1.media.nama_majalah), stdin);
        koleksi1.media.nama_majalah[strcspn(koleksi1.media.nama_majalah, "\n")] = '\0';
    }

    tampilkanKoleksi(koleksi1);

    return 0;
}
