package pekan1_2511532005;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import Pekan2_2511532005.Transaksi;

public class Rekening {
	String nomorRekening;
	String namaPemilik;
	double saldo;
	
	ArrayList<Transaksi>riwayatTransaksi;
	
	public Rekening(String nomor, String nama, double saldoAwal) {
		this.nomorRekening = nomor;
		this.namaPemilik = nama;
		this.saldo = saldoAwal;
		
		this.riwayatTransaksi = new ArrayList<>();
		
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("Rekening atas nama" + namaPemilik + " berhasil dibuat dengan saldo Rp" + saldo);
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
