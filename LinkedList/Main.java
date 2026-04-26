// ============================================
// File: Main.java
// Sistem Kasir Toko menggunakan Queue & Stack
// ============================================

import java.util.Scanner;

// ----- Class Node untuk menyimpan data pelanggan -----
class Node {
    String kode;       // nomor antrian
    String nama;       // nama pelanggan
    double total;      // total belanja
    Node next;         // pointer ke node berikutnya

    public Node(String kode, String nama, double total) {
        this.kode = kode;
        this.nama = nama;
        this.total = total;
        this.next = null;
    }
}

// ----- Class Queue (FIFO) untuk antrian pelanggan -----
class Queue {
    private Node front; // node terdepan (akan dilayani duluan)
    private Node rear;  // node terbelakang (paling baru masuk)
    private int jumlah;
    private final int MAX = 5; // batas maksimal antrian

    public Queue() {
        this.front = null;
        this.rear = null;
        this.jumlah = 0;
    }

    // Cek antrian penuh
    public boolean isFull() {
        return jumlah >= MAX;
    }

    // Cek antrian kosong
    public boolean isEmpty() {
        return front == null;
    }

    // Enqueue: tambah pelanggan ke belakang antrian
    public void enqueue(String kode, String nama, double total) {
        if (isFull()) {
            System.out.println("Antrian penuh! Maksimal " + MAX + " pelanggan.");
            return;
        }
        Node baru = new Node(kode, nama, total);
        if (isEmpty()) {
            front = baru;
            rear = baru;
        } else {
            rear.next = baru;
            rear = baru;
        }
        jumlah++;
        System.out.println("Data pelanggan ditambahkan ke antrian!");
    }

    // Dequeue: ambil pelanggan terdepan (yang dilayani)
    public Node dequeue() {
        if (isEmpty()) {
            System.out.println("Antrian kosong, tidak ada pelanggan untuk dilayani.");
            return null;
        }
        Node dilayani = front;
        front = front.next;
        if (front == null) rear = null; // antrian jadi kosong
        jumlah--;
        return dilayani;
    }

    // Tampilkan seluruh antrian
    public void tampilkan() {
        if (isEmpty()) {
            System.out.println("Antrian masih kosong.");
            return;
        }
        System.out.println("Antrian Pelanggan Saat Ini:");
        Node temp = front;
        int urut = 1;
        while (temp != null) {
            System.out.println(urut + ". Kode: " + temp.kode +
                               " | Nama: " + temp.nama +
                               " | Total: Rp" + (long) temp.total);
            temp = temp.next;
            urut++;
        }
        System.out.println("Total Antrian: " + jumlah);
    }
}

// ----- Class Stack (LIFO) untuk riwayat transaksi -----
class Stack {
    private Node top; // node teratas (transaksi terbaru)
    private int jumlah;

    public Stack() {
        this.top = null;
        this.jumlah = 0;
    }

    // Cek stack kosong
    public boolean isEmpty() {
        return top == null;
    }

    // Push: simpan transaksi ke stack
    public void push(String kode, String nama, double total) {
        Node baru = new Node(kode, nama, total);
        baru.next = top; // node baru menunjuk top lama
        top = baru;      // top sekarang = node baru
        jumlah++;
    }

    // Display: tampilkan riwayat dari terbaru ke lama
    public void display() {
        if (isEmpty()) {
            System.out.println("Belum ada riwayat transaksi.");
            return;
        }
        System.out.println("Riwayat Transaksi (terbaru -> lama):");
        Node temp = top;
        int urut = 1;
        while (temp != null) {
            System.out.println(urut + ". Kode: " + temp.kode +
                               " | Nama: " + temp.nama +
                               " | Total: Rp" + (long) temp.total);
            temp = temp.next;
            urut++;
        }
        System.out.println("Total Transaksi: " + jumlah);
    }
}

// ----- Class Main (entry point) -----
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Queue antrian = new Queue();
        Stack riwayat = new Stack();
        int pilih;

        do {
            System.out.println("\n=== SISTEM KASIR TOKO ===");
            System.out.println("1. Tambah Antrian");
            System.out.println("2. Layani Pelanggan");
            System.out.println("3. Tampilkan Antrian");
            System.out.println("4. Lihat Riwayat Transaksi");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");

            try {
                pilih = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Input harus berupa angka!");
                pilih = 0;
                continue;
            }

            switch (pilih) {
                case 1:
                    // Enqueue: input data pelanggan baru
                    System.out.print("Masukkan Nomor Antrian: ");
                    String kode = sc.nextLine();
                    System.out.print("Masukkan Nama Pelanggan: ");
                    String nama = sc.nextLine();
                    System.out.print("Masukkan Total Belanja: ");
                    double total;
                    try {
                        total = Double.parseDouble(sc.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Total belanja harus berupa angka!");
                        break;
                    }
                    antrian.enqueue(kode, nama, total);
                    break;

                case 2:
                    // Dequeue + push ke stack riwayat
                    Node dilayani = antrian.dequeue();
                    if (dilayani != null) {
                        System.out.println("Melayani pelanggan " + dilayani.kode +
                                           " (" + dilayani.nama + ")");
                        riwayat.push(dilayani.kode, dilayani.nama, dilayani.total);
                        System.out.println("Transaksi disimpan ke riwayat.");
                    }
                    break;

                case 3:
                    antrian.tampilkan();
                    break;

                case 4:
                    riwayat.display();
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
