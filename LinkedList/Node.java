public class Node {
    // Atribut data buku
    String kodeBuku;
    String judul;
    String penulis;
 
    // Pointer ke node berikutnya
    Node next;
 
    // Constructor untuk inisialisasi node baru
    public Node(String kodeBuku, String judul, String penulis) {
        this.kodeBuku = kodeBuku;
        this.judul = judul;
        this.penulis = penulis;
        this.next = null; 
    }
}
