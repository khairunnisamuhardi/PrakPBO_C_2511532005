package pekan1_2511532005;
import java.text.NumberFormat;
import java.util.Locale;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		nomorRekening = nomor;
		namaPemilik = nama;
		saldo = saldoAwal;
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Rekening atas nama" + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
	}
	
	public void setorTunai(double nominal) {
		if (nominal > 10000) {
			saldo += nominal;
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
		System.out.println("Saldo Akhir : Rp" + rupiah.format(saldo));
		System.out.println("----------------------");
	}
	
	public void tarikTunai(double nominal) {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		
		if (nominal < 10000) {
			System.out.println("Transaksi Gagal! Minimal nominal penarikan Rp 10.000");
		} else if (nominal > saldo){
			System.out.println("Transaksi Gagal! Saldo anda tidak mencukupi. Saldo anda : Rp " + rupiah.format(saldo));
		} else {
			saldo -= nominal;
			System.out.println("Tarik tunai Rp" + rupiah.format(nominal) + " berhasil. Saldo akhir anda : Rp " + rupiah.format(saldo));
		}
	}
}
