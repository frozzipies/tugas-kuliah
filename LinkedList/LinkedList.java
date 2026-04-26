public class LinkedList {
    private Node head; // pointer ke node pertama
    private int jumlah; // jumlah buku saat ini
 
    public LinkedList() {
        this.head = null;
        this.jumlah = 0;
    }
 
    // ----- Operasi Tambah Buku (Push di akhir) -----
    public void tambahBuku(String kode, String judul, String penulis) {
        Node baru = new Node(kode, judul, penulis);
 
        // Jika list masih kosong, baru menjadi head
        if (head == null) {
            head = baru;
        } else {
            // Telusuri sampai node terakhir
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            // Sambungkan node terakhir ke node baru
            temp.next = baru;
        }
        jumlah++;
        System.out.println("Data berhasil ditambahkan!");
    }
 
    // ----- Operasi Hapus Buku (Pop dari akhir) -----
    public void hapusBuku() {
        if (head == null) {
            System.out.println("Tidak ada data untuk dihapus.");
            return;
        }
 
        // Jika hanya ada 1 node
        if (head.next == null) {
            System.out.println("Buku '" + head.judul + "' berhasil dihapus.");
            head = null;
        } else {
            // Cari node sebelum node terakhir
            Node temp = head;
            while (temp.next.next != null) {
                temp = temp.next;
            }
            System.out.println("Buku '" + temp.next.judul + "' berhasil dihapus.");
            temp.next = null; // putuskan node terakhir
        }
        jumlah--;
    }
 
    // ----- Operasi Cari Buku berdasarkan kodeBuku -----
    public void cariBuku(String kode) {
        Node temp = head;
        while (temp != null) {
            if (temp.kodeBuku.equalsIgnoreCase(kode)) {
                System.out.println("Buku ditemukan:");
                System.out.println("Kode    : " + temp.kodeBuku);
                System.out.println("Judul   : " + temp.judul);
                System.out.println("Penulis : " + temp.penulis);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Buku tidak ditemukan.");
    }
 
    // ----- Operasi Tampilkan Semua Buku -----
    public void tampilkanSemua() {
        if (head == null) {
            System.out.println("Daftar buku masih kosong.");
            return;
        }
 
        System.out.println("Daftar Buku:");

        Node temp = head;
        while (temp != null) {
            System.out.println("Kode: " + temp.kodeBuku +
                               " | Judul: " + temp.judul +
                               " | Penulis: " + temp.penulis);
            temp = temp.next;
        }
        System.out.println("Total Buku: " + jumlah);
    }
}
