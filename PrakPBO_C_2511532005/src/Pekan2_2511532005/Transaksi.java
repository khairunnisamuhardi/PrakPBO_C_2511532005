package Pekan2_2511532005;

import java.text.NumberFormat;
import java.util.Locale;
import java.text.NumberFormat;

public class Transaksi {
	String idTransaksi;
	String jenis;
	double nominal;
	
	public Transaksi(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public void cetakDetail() {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("ID:" + idTransaksi + "| Jenis:" + jenis + "| Nominal : " + rupiah.format(nominal));
	}
}
