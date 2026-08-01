class SalesSummary {
    double total;
    double average;
    double maximum;

    public SalesSummary(double total, double average, double maximum) {
        this.total = total;
        this.average = average;
        this.maximum = maximum;
    }
}

public class Main {
    public static SalesSummary calculateSalesSummary(double[] sales) {
        if (sales == null || sales.length == 0) {
            return new SalesSummary(0, 0, 0);
        }

        double total = 0;
        double maximum = sales[0];

        // วนลูปอ่านข้อมูลเพียงรอบเดียว
        for (int i = 0; i < sales.length; i++) {
            total += sales[i];
            if (sales[i] > maximum) {
                maximum = sales[i];
            }
        }

        double average = total / sales.length;

        return new SalesSummary(total, average, maximum);
    }

    public static void main(String[] args) {
        double[] sales = {1250.50, 890.00, 1575.25, 2300.00, 940.75};
        SalesSummary summary = calculateSalesSummary(sales);

        System.out.println("Total: " + summary.total);
        System.out.println("Average: " + summary.average);
        System.out.println("Maximum: " + summary.maximum);
    }
}
