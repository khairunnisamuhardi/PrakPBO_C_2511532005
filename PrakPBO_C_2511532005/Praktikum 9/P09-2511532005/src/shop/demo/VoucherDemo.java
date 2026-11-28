package shop.demo;

import shop.model.Voucher;
import shop.service.Rupiah;

/** Demo pembuatan Voucher sah dan tidak sah menggunakan Voucher.Builder. */
public class VoucherDemo {

    public static void main(String[] args) {
        Voucher sah = new Voucher.Builder("HEMAT10", 10)
                .minimumPurchase(100_000)
                .description("Diskon 10% minimal belanja Rp100.000")
                .build();
        System.out.println("Berhasil: " + sah.getCode() + " (" + sah.getPercent() + "%), "
                + "min. " + Rupiah.format(sah.getMinimumPurchase()) + " - " + sah.getDescription());

        tryBuild("Voucher persen > 50", () -> new Voucher.Builder("DISKON75", 75).build());
    }

    private static void tryBuild(String label, Runnable action) {
        try {
            action.run();
            System.out.println(label + ": DITERIMA (seharusnya ditolak!)");
        } catch (IllegalStateException e) {
            System.out.println(label + ": ditolak -> " + e.getMessage());
        }
    }
}
