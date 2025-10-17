#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX 100
#define FILE_NAME "siswa.txt"

// Struktur data siswa
struct Siswa {
    char nama[50];
    int umur;
    char reg[20];
};

// Add data
void tambahData() {
    FILE *fp;
    struct Siswa s;
    
    fp = fopen(FILE_NAME, "a");
    if (fp == NULL) {
        printf("Gagal membuka file!\n");
        return;
    }

    printf("Masukkan Nama Siswa: ");
    scanf(" %[^\n]", s.nama);
    printf("Masukkan Umur Siswa: ");
    scanf("%d", &s.umur);
    printf("Masukkan Nomor Registrasi: ");
    scanf(" %s", s.reg);

    fprintf(fp, "%s,%d,%s\n", s.nama, s.umur, s.reg);
    fclose(fp);

    printf("✅ Data berhasil ditambahkan!\n\n");
}

// Get data
void cariData() {
    FILE *fp;
    struct Siswa s;
    char line[100];
    char cariReg[20];
    int found = 0;

    fp = fopen(FILE_NAME, "r");
    if (fp == NULL) {
        printf("File tidak ditemukan!\n");
        return;
    }

    printf("Masukkan Nomor Registrasi yang ingin dicari: ");
    scanf(" %s", cariReg);

    while (fgets(line, sizeof(line), fp)) {
        char *token = strtok(line, ",");
        strcpy(s.nama, token);

        token = strtok(NULL, ",");
        s.umur = atoi(token);

        token = strtok(NULL, ",\n");
        strcpy(s.reg, token);

        if (strcmp(s.reg, cariReg) == 0) {
            printf("\n=== DATA DITEMUKAN ===\n");
            printf("Nama  : %s\n", s.nama);
            printf("Umur  : %d\n", s.umur);
            printf("Reg   : %s\n", s.reg);
            found = 1;
            break;
        }
    }

    if (!found)
        printf("⚠️ Data tidak ditemukan!\n");

    fclose(fp);
    printf("\n");
}

// Delete data
void hapusData() {
    FILE *fp, *temp;
    struct Siswa s;
    char line[100];
    char hapusReg[20];
    int found = 0;

    fp = fopen(FILE_NAME, "r");
    temp = fopen("temp.txt", "w");
    if (fp == NULL || temp == NULL) {
        printf("Gagal membuka file!\n");
        return;
    }

    printf("Masukkan Nomor Registrasi yang ingin dihapus: ");
    scanf(" %s", hapusReg);

    while (fgets(line, sizeof(line), fp)) {
        char *token = strtok(line, ",");
        strcpy(s.nama, token);

        token = strtok(NULL, ",");
        s.umur = atoi(token);

        token = strtok(NULL, ",\n");
        strcpy(s.reg, token);

        // Jika nomor registrasi berbeda → simpan ke file baru
        if (strcmp(s.reg, hapusReg) != 0) {
            fprintf(temp, "%s,%d,%s\n", s.nama, s.umur, s.reg);
        } else {
            found = 1;
        }
    }

    fclose(fp);
    fclose(temp);

    remove(FILE_NAME);          // hapus file lama
    rename("temp.txt", FILE_NAME); // ganti file sementara ke nama asli

    if (found)
        printf("✅ Data berhasil dihapus!\n\n");
    else
        printf("⚠️ Data tidak ditemukan!\n\n");
}

// Menu utama
void menu() {
    printf("=====================================\n");
    printf("      SISTEM DATABASE SISWA\n");
    printf("=====================================\n");
    printf("1. Tambah Data Siswa\n");
    printf("2. Hapus Data Siswa\n");
    printf("3. Cari Data Siswa\n");
    printf("4. Keluar\n");
    printf("=====================================\n");
}

int main() {
    int pilihan;

    do {
        menu();
        printf("Pilih menu [1-4]: ");
        scanf("%d", &pilihan);

        switch (pilihan) {
            case 1:
                tambahData();
                break;
            case 2:
                hapusData();
                break;
            case 3:
                cariData();
                break;
            case 4:
                printf("Terima kasih, program selesai.\n");
                break;
            default:
                printf("Pilihan tidak valid!\n\n");
        }
    } while (pilihan != 4);

    return 0;
}
