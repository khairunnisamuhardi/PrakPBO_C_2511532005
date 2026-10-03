package pekan4;

public class RekeningGiro extends Rekening_Pekan4{
	private double batasOverdraft;
	
	public RekeningGiro(String nomor, String nama, double saldoAwal, String pinAwal, double batasOverdraft) {
		//memanggil inisialisasi dasar dari superClass
		super (nomor, nama, saldoAwal, pinAwal);
		this.batasOverdraft = batasOverdraft;
	}
	//getter khusus giro
	public double getBatasOverdraft() {
		return batasOverdraft;
	}
	// (Catatan: penarikan hingga limit overdrfat akan diselesaikan di modul 5)
}