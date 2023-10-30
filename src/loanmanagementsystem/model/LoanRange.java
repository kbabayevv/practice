package loanmanagementsystem.model;

public class LoanRange  {
    private double min;
    private double max;

    public LoanRange(double min, double max) {
        this.min = min;
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public double getMax() {
        return max;
    }
}
