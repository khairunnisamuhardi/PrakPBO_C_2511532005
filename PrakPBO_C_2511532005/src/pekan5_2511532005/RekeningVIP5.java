package pekan5_2511532005;

import pekan5_2511532005.rekeningPekan5;

public class RekeningVIP5 extends rekeningPekan5{
	public RekeningVIP5(String nomor, String nama, double saldoAwal, String pinAwal) {
		super (nomor, nama, saldoAwal + 100000.0 , pinAwal);
	}

}
