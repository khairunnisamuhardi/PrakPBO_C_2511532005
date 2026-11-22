package shop.shipping;

/** Aturan ongkir: cukup tahu berat (gram) untuk menghitung biaya. */
public interface ShippingRate {
    long costFor(int grams);
}