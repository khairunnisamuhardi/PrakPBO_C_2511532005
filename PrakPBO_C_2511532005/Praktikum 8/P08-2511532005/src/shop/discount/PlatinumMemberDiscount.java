package shop.discount;

/** Member PLATINUM: potongan tetap 20%. */
public class PlatinumMemberDiscount implements DiscountStrategy {
    @Override
    public long discountFor(long subtotal) {
        return subtotal * 20 / 100;
    }
}