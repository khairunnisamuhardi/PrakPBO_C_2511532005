package pekan5_2511532005;

import pekan5_2511532005.rekeningPekan5;

public class RekeningGiro5 extends rekeningPekan5{
	private double batasOverdraft;
	
	public RekeningGiro5(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari superClass
		super (nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	
	// implementasi polimorfisme overriding
	@Override
    public boolean tarikTunai(double nominal) {
        if (nominal <= 0) {
            System.out.println("Gagal: Nominal tarik Giro harus lebih dari 0!");
            return false;
        }
        // Logika Khusus Giro: Boleh ditarik asalkan nominal tidak melebihi (saldo + batasOverdraft)
        else if (nominal > (saldo + batasOverdraft)) {
            System.out.println("Transaksi Gagal: Melebihi batas overdraft! Maksimal penarikan: Rp" + (saldo + batasOverdraft));
            return false;
        } else {
            saldo -= nominal; // Saldo bisa bernilai negatif di sini (memakan overdraft)

            String idTrx = "TRX-G-" + System.currentTimeMillis();
            riwayatTransaksi.add(new TransaksiPekan5(idTrx, "Debit Giro", nominal));

            System.out.println("Tarik tunai Giro Rp" + nominal + " berhasil. Saldo saat ini: Rp" + saldo);
            return true;
        }
    }
	//getter khusus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
	// (Catatan: penarikan hingga limit overdrfat akan diselesaikan di modul 5)
}