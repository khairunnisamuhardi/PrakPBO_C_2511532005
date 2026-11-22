package shop.shipping;

/** Ongkir reguler: tarif per kg (dibulatkan ke atas per kg). */
public class RegularRate implements ShippingRate {
    private final long ratePerKg;

    public RegularRate(long ratePerKg) {
        this.ratePerKg = ratePerKg;
    }

    @Override
    public long costFor(int grams) {
        long kg = (grams + 999) / 1000; // bulatkan ke atas
        return kg * ratePerKg;
    }
}