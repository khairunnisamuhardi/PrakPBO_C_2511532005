package pekan1_2511532005;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Rekening {
	private String nomorRekening;
	private String namaPemilik;
	private double saldo;
	private String pin;
	private int percobaanSalah = 0;
	private boolean isTerblokir = false;
	
	private ArrayList<Transaksi> riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal, String pinAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		if (pinAwal.length() == 6) {
			this.pin = pinAwal;
		} else {
			System.out.println("Peringatan: PIN harus 6 digit! menggunakan PIN default 123456");
			this.pin = "123456";
		}
		this.riwayatTransaksi = new ArrayList<>();
		System.out.println("Rekening atas nama " + namaPemilik + " berhasil dibuat.");
		
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Rekening atas nama" + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public String getNomorRekening() { return nomorRekening;}
	public String getNamaPemilik() { return namaPemilik; }
	
	public boolean isTerblokir() {return isTerblokir;}
	
	public boolean otentikasi(String inputPin) {
		if (isTerblokir) {
            System.out.println("Akun Anda terblokir! Tidak dapat melakukan transaksi.");
            return false;
        }
		if (this.pin.equals(inputPin)) {
            percobaanSalah = 0; // Reset counter jika benar
            return true;
        } else {
            percobaanSalah++;
            System.out.println("PIN salah! Kesempatan tersisa: " + (3 - percobaanSalah));
            
            // Jika sudah 3 kali salah, blokir akun
            if (percobaanSalah >= 3) {
                isTerblokir = true;
                System.out.println("Akun Anda terblokir karena salah memasukkan PIN 3 kali!");
            }
        } return false;
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 10000) {
			saldo += nominal;
			
			String idTrx = "TRX-S-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Kredit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Setor tunai Rp" + nominal + " berhasil. Saldo saat ini : Rp" + saldo);
		} else {
			System.out.println("Gagal: Nominal setor harus lebih dari 10.000!");
		}
	}
	
	public void cekInformasi() {
		System.out.println("--- INFO REKENING ---");
		System.out.println("No. Rekening : " + nomorRekening);
		System.out.println("Nama Pemiliki : " + namaPemilik);
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Saldo Akhir : " + rupiah.format(saldo));
		System.out.println("----------------------");
	}
	
	public void tarikTunai(double nominal) {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal! Minimal nominal penarikan Rp 10.000");
		} else if (nominal > saldo){
			System.out.println("Transaksi Gagal! Saldo anda tidak mencukupi. Saldo anda : " + rupiah.format(saldo));
		} else {
			saldo -= nominal;
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			Transaksi trxBaru = new Transaksi(idTrx, "Debit", nominal);
			riwayatTransaksi.add(trxBaru);
			System.out.println("Tarik tunai " + rupiah.format(nominal) + " berhasil. Saldo akhir anda :" + rupiah.format(saldo));
		}
	}
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini !");
		} else {
			System.out.println("--- Mutasi Rekening ---");
			
			for (Transaksi trx : riwayatTransaksi) {
				trx.cetakDetail();
			}
			System.out.println("------------");
		}
	}
	
	public void riwayatPenarikan() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada Transaksi pada rekening ini ");
		} else {
			System.out.println("----- 3 RIWAYAT TRANSAKSI ---");
			int jumlah = Math.min(3, riwayatTransaksi.size());
			for (int i = riwayatTransaksi.size() - 1; i >= riwayatTransaksi.size() - jumlah; i--) {
				riwayatTransaksi.get(i).cetakDetail();
			}
			System.out.println("---------------------");
		}
	}
	}
