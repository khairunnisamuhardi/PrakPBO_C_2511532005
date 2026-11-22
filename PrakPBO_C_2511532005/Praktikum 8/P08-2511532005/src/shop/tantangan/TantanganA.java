package shop.tantangan;

import shop.shipping.*;

public class TantanganA {
    public static void main(String[] args) {
        ShippingRate regular = new RegularRate(8_000);
        ShippingRate sameDay = new SameDayRate();

        System.out.println("Regular 2 kg : " + regular.costFor(2_000)); // 16000
        System.out.println("SameDay 2 kg : " + sameDay.costFor(2_000)); // 25000
        System.out.println("SameDay 5 kg : " + sameDay.costFor(5_000)); // 45000
        // Lalu buat OrderCalculator dengan new OrderCalculator(sameDay)
        // dan hitung satu pesanan agar terlihat ongkirnya berubah.
    }
}