package pekan1_2511532005;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		ArrayList<Rekening> daftarRekening = new ArrayList<>();
		Rekening akunAktif = null;
		boolean isRunning = true;
		
		System.out.println("=== SISTEM PERBANKAN MINI ===");
			
		while (isRunning) {
			System.out.println("\nMenu Utama :");
			System.out.println("1. Buka Rekening Baru");
			System.out.println("2. Setor Tunai");
			System.out.println("3. Tarik Tunai");
			System.out.println("4. Cek Informasi Rekening");
			System.out.println("5. Ganti/Pilih Akun Aktif");
			System.out.println("6. Cetak Mutasi(Riwayat)");
			System.out.println("7. Riwayat Penarikan");
			System.out.println("0. Keluar");
			System.out.println("Pilih Menu :");
			
			int pilihan = 0;
			try {
				pilihan = input.nextInt();
				input.nextLine();
			} catch (Exception e) {
                System.out.println("Maaf tidak bisa memproses, hanya bisa masukkan angka saja!");
                input.nextLine();
                continue;
            }
			
			switch (pilihan) {
			case 1:
				System.out.print("Masukkan No Rekening :");
				String no = input.nextLine();
				System.out.print("Masukkan Nama Pemilik :");
				String nama = input.nextLine();
				String pin = "";
				while (true) {
					System.out.print("Masukkan PIN anda (6 digit) : ");
					pin = input.nextLine();
					
					if (pin.matches("\\d{6}")) {
						break;
					} else {
						System.out.println("PIN tidak valid! Harus berupa 6 digit angka. silahkan coba lagi.");
					}
				}
				
				System.out.print("Masukkan Saldo Awal :");
				try {
                    double saldo = input.nextDouble();
                    input.nextLine();
                    Rekening rekeningBaru = new Rekening(no, nama, saldo, pin);
                    daftarRekening.add(rekeningBaru);
                    akunAktif = rekeningBaru;
                    System.out.println("Akun berhasil dibuat dan otomatis menjadi akun aktif!");
                } catch (Exception e) {
                    System.out.println("Error: Saldo awal harus berupa angka!");
                    input.nextLine();
                }
                break;
			
			case 2:
				if (akunAktif != null) {
			        // Cek apakah akun terblokir
			        if (akunAktif.isTerblokir()) {
			            System.out.println("Akses Ditolak: Akun Anda terblokir!");
			            break;
			        }
			        
			        System.out.print("Masukkan nominal setor tunai: ");
			        double setor = input.nextDouble();
			        input.nextLine();
			        akunAktif.setorTunai(setor);
			    } else {
			        System.out.println("Buat rekening terlebih dahulu di Menu 1!");
			    }
			    break;
			
			case 3:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					if (akunAktif.isTerblokir()) {
			            System.out.println("Akses Ditolak: Akun Anda sudah terblokir!");
			            break;
			        }
					System.out.print ("Masukkan PIN untuk verifikassi: ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						System.out.print ("Masukkan nominal tarik tunai: ");
						double tarik = input.nextDouble();
						akunAktif.tarikTunai(tarik);
					} else {
						System.out.println("Akses ditolak: Pin yang dimasukkan salah");
					} 
					
				}
				break;
				
			case 4:
				if (akunAktif == null) {
					System.out.println("Error : Anda belum membuka rekening!");			
				} else {
					akunAktif.cekInformasi();
				}
				break;
				
			case 5:
                if (daftarRekening.isEmpty()) {
                    System.out.println("Belum ada rekening yang terdaftar di sistem!");
                } else {
                    System.out.print("Masukkan Nomor Rekening yang ingin diaktifkan: ");
                    String noCari = input.nextLine();
                    
                    boolean ditemukan = false;
                    for (Rekening rek : daftarRekening) {
                        if (rek.getNomorRekening().equals(noCari)) {
                            akunAktif = rek;
                            ditemukan = true;
                            System.out.println("Berhasil berganti ke akun milik: " + akunAktif.getNamaPemilik());
                            break;
                        }
                    }
                    
                    if (!ditemukan) {
                        System.out.println("Nomor rekening " + noCari + " tidak ditemukan!");
                    }
                }
                break;
                
			case 6:
				if (akunAktif == null) {
					System.out.println("Error: Anda belum membuka rekening!");
				} else {
					System.out.print ("Masukkan PIN untuk verifikassi: ");
					String pinInput = input.nextLine();
					if (akunAktif.otentikasi(pinInput)) {
						System.out.print ("Masukkan nominal tarik tunai: ");
						double cetakMutasi = input.nextDouble();
						akunAktif.cetakMutasi();
					} else {
						System.out.println("Akses ditolak: Pin yang dimasukkan salah");
					} 
					
				}
				break;
			
			case 7:
				if(akunAktif == null) {
					System.out.println("Error : Mohon maaf, anda belum memiliki norek!");
				}else {
					akunAktif.riwayatPenarikan();
				}
                
			case 0:
				isRunning = false;
				System.out.println("Sistem ditutup. Terima Kasih!");
				break;
			
			default:
				System.out.println("Pilihan tidak valid!");
			}
		}
		input.close();
	}
	

}
