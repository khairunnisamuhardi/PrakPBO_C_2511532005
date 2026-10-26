package pekan5_2511532005;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class rekeningPekan5 {
	private String nomorRekening;
	private String namaPemilik;
	protected double saldo;
	private String pin;
	private int percobaanSalah = 0;
	private boolean isTerblokir = false;
	
	protected ArrayList<TransaksiPekan5> riwayatTransaksi;
	
	public rekeningPekan5(String nomor, String nama, double saldoAwal, String pinAwal) {
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
			TransaksiPekan5 trxBaru = new TransaksiPekan5(idTrx, "Kredit", nominal);
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
	
	public boolean tarikTunai(double nominal) {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal! Minimal nominal penarikan Rp 10.000");
			return false;
		} else if (nominal > saldo){
			System.out.println("Transaksi Gagal! Saldo anda tidak mencukupi. Saldo anda : " + rupiah.format(saldo));
			return false;
		} else {
			saldo -= nominal;
			String idTrx = "TRX-T-" + System.currentTimeMillis();
			TransaksiPekan5 trxBaru = new TransaksiPekan5(idTrx, "Debit", nominal);
			riwayatTransaksi.add(new TransaksiPekan5(idTrx, "Debit", nominal));
			System.out.println("Tarik tunai " + rupiah.format(nominal) + " berhasil. Saldo akhir anda :" + rupiah.format(saldo));
			return true;
		}
	}
	public void cetakMutasi() {
		if (riwayatTransaksi.isEmpty()) {
			System.out.println("Belum ada transaksi pada rekening ini !");
		} else {
			System.out.println("--- Mutasi Rekening ---");
			
			for (TransaksiPekan5 trx : riwayatTransaksi) {
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
	
	public void transfer(rekeningPekan5 tujuan, double nominal, String pinInput) {
        // 1. Otentikasi PIN pengirim
        if (!this.otentikasi(pinInput)) {
            System.out.println("Gagal Transfer: PIN yang Anda masukkan salah!");
            return;
        }

        // 2. Eksekusi penarikan dari rekening pengirim (Dynamic Dispatch)
        boolean tarikSukses = this.tarikTunai(nominal);

        // 3. Jika penarikan berhasil, setor ke rekening tujuan
        if (tarikSukses) {
            tujuan.setorTunai(nominal);
            System.out.println("Transfer sebesar Rp" + nominal + " ke rekening " + tujuan.getNamaPemilik() + " (" + tujuan.getNomorRekening() + ") berhasil!");
        } else {
            System.out.println("Transfer gagal diproses.");
        }
    }
		
	}
	
