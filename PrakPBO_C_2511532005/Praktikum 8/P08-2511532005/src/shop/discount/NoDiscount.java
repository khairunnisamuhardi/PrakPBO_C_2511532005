package shop.discount;

/** Member biasa: tanpa potongan. */
public class NoDiscount implements DiscountStrategy {
    @Override
    public long discountFor(long subtotal) {
        return 0;
    }
}