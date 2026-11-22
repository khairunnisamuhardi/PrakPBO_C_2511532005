package shop.payment;

import shop.exception.PaymentFailedException;

/** Pembayaran QRIS: maksimal Rp10.000.000 per transaksi. */
public class QrisPayment implements PaymentMethod {
    private static final long MAX_AMOUNT = 10_000_000;

    @Override
    public String name() {
        return "QRIS";
    }

    @Override
    public void pay(long amount) throws PaymentFailedException {
        if (amount > MAX_AMOUNT) {
            throw new PaymentFailedException("transaksi QRIS maksimal Rp10.000.000");
        }
    }
}