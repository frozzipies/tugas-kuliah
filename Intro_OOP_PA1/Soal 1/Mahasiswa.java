public class Mahasiswa {
    // Atribut dengan modifier private (Enkapsulasi)
    private String nama;
    private String nim;
    private String jurusan;
    private double ipk;

    // Constructor untuk menginisialisasi data mahasiswa
    public Mahasiswa(String nama, String nim, String jurusan, double ipk) {
        this.nama = nama;
        this.nim = nim;
        this.jurusan = jurusan;
        this.ipk = ipk;
    }

    // Method public untuk menampilkan informasi mahasiswa
    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("NIM: " + nim);
        System.out.println("Jurusan: " + jurusan);
        System.out.println("IPK: " + ipk);
        System.out.println(); // Baris baru untuk kerapian
    }
    
    // Method tambahan untuk mengecek status kelulusan (Logic)
    public String getStatusKelulusan() {
        return (ipk >= 2.0) ? "Lulus" : "Tidak Lulus";
    }
}
