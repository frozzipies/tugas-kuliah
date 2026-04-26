import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList daftar = new LinkedList();
        int pilih;
 
        do {
            // Tampilkan menu utama
            System.out.println("\n===== SISTEM DATA BUKU =====");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Hapus Buku");
            System.out.println("3. Cari Buku");
            System.out.println("4. Lihat Semua Buku");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilih = Integer.parseInt(sc.nextLine());
 
            switch (pilih) {
                case 1:
                    // Input data buku baru
                    System.out.print("Masukkan Kode Buku: ");
                    String kode = sc.nextLine();
 
                    // Validasi: kodeBuku maksimal 5 karakter
                    if (kode.length() > 5) {
                        System.out.println("Kode buku maksimal 5 karakter!");
                        break;
                    }
 
                    System.out.print("Masukkan Judul: ");
                    String judul = sc.nextLine();
                    System.out.print("Masukkan Penulis: ");
                    String penulis = sc.nextLine();
 
                    daftar.tambahBuku(kode, judul, penulis);
                    break;
 
                case 2:
                    daftar.hapusBuku();
                    break;
 
                case 3:
                    System.out.print("Masukkan Kode Buku: ");
                    String cari = sc.nextLine();
                    daftar.cariBuku(cari);
                    break;
 
                case 4:
                    daftar.tampilkanSemua();
                    break;
 
                case 5:
                    System.out.println("Terima kasih, program selesai.");
                    break;
 
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        } while (pilih != 5);
 
        sc.close();
    }
}
