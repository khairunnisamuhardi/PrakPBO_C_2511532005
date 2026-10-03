package pekan4;

import java.text.NumberFormat;
import java.util.Locale;

public class Transaksi_Pekan4 {
	private String idTransaksi;
	private String jenis;
	private double nominal;
	
	public Transaksi_Pekan4(String id, String jenis, double nominal) {
		this.idTransaksi = id;
		this.jenis = jenis;
		this.nominal = nominal;
	}
	
	public String getIdTransaksi() {return idTransaksi;}
	public String getJenis() {return jenis;}
	public double getNominal() {return nominal;}
	
	
	public void cetakDetail() {
		NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
		System.out.println("ID:" + idTransaksi + "| Jenis:" + jenis + "| Nominal : " + rupiah.format(nominal));
	}
}
