public class Visit {
    private int visitId;
    private String petName;
    private String reason;
    private String status;

    public Visit(int visitId, String petName, String reason, String status) {
        this.visitId = visitId;
        this.petName = petName;
        this.reason = reason;
        this.status = status;
    }

    public void completeVisit() {
        status = "Completed";
    }

    public boolean isPending() {
        return status.equalsIgnoreCase("Pending");
    }

    @Override
    public String toString() {
        return "Visit ID: " + visitId +
                ", Pet: " + petName +
                ", Reason: " + reason +
                ", Status: " + status;
    }
}