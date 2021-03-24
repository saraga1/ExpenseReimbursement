package dev.araga.entities;



public class Expenses {


    private int expenseId;
    private int amount;
    private String reason; //The reason for the request an expense.
    private String status; //It can either be pending, approved, or denied
    private String managerInput;
    private long dateSubmitted;
    private long dateOfResult;
    private int employeeId;

    public Expenses() {
    }

    public Expenses(int expenseId, int amount, String reason, String status, String managerInput, long dateSubmitted, long dateOfResult, int employeeId) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.reason = reason;
        this.status = status;
        this.managerInput = managerInput;
        this.dateSubmitted = dateSubmitted;
        this.dateOfResult = dateOfResult;
        this.employeeId = employeeId;
    }

    public String getManagerInput() {
        return managerInput;
    }

    public void setManagerInput(String managerInput) {
        this.managerInput = managerInput;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(long dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public long getDateOfResult() {
        return dateOfResult;
    }

    public void setDateOfResult(long dateOfResult) {
        this.dateOfResult = dateOfResult;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Expenses{" +
                "expenseId=" + expenseId +
                ", amount=" + amount +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                ", managerInput='" + managerInput + '\'' +
                ", dateSubmitted=" + dateSubmitted +
                ", dateOfResult=" + dateOfResult +
                ", employeeId=" + employeeId +
                '}';
    }
}
