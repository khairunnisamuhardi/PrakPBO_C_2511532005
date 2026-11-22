package shop.shipping;

/** Same Day: Rp25.000 sampai 3 kg, lalu Rp10.000 per kg berikutnya. */
public class SameDayRate implements ShippingRate {
    private static final long BASE = 25_000L;
    private static final int BASE_GRAMS = 3_000;
    private static final long PER_EXTRA_KG = 10_000L;

    @Override
    public long costFor(int grams) {
        if (grams <= BASE_GRAMS) {
            return BASE;
        }
        long extraKg = (grams - BASE_GRAMS + 999) / 1000; // bulatkan ke atas
        return BASE + extraKg * PER_EXTRA_KG;
    }
}