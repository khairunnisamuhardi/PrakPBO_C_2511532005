package pekan4;

public class RekeningVIP extends Rekening_Pekan4 {
	public RekeningVIP(String nomor, String nama, double saldoAwal, String pinAwal) {
		super (nomor, nama, saldoAwal + 100000.0 , pinAwal);
	}

}
