package pekan5_2511532005;

import pekan5_2511532005.TransaksiPekan5;
import pekan5_2511532005.rekeningPekan5;

public class RekeningTabungan5 extends rekeningPekan5{
	//Atribut spesifik yang hanya dimiliki oleh Tabungan
	private double sukuBunga;
	
	//Constructor Subclass
	public RekeningTabungan5 (String nomor, String nama, double saldoAwal, String pinAwal, double sukuBunga) {
		super (nomor, nama, saldoAwal, pinAwal);
		this.sukuBunga = sukuBunga;
	}
	public void tambahBungaAkhirBulan() {
		//Menghitung bunga
		//mengapa bisa mengakses saldo secara langsung dari class RekeningTabungan?
		double nominalBunga = saldo * (sukuBunga / 100);
		saldo += nominalBunga;
		
		//Mencatat riwaya transaksi
		String idTrx = "TRX-B-" + System.currentTimeMillis();
		riwayatTransaksi.add(new TransaksiPekan5 (idTrx, "Bunga", nominalBunga));
		
		System.out.println("Bunga " + sukuBunga + "% berhasil ditambahkan: Rp"+ nominalBunga);
	}
}