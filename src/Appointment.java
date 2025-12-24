public class Appointment {
    private String petName;
    private String vetName;
    private String reason;
    private double cost;

    public Appointment(String petName, String vetName, String reason, double cost) {
        this.petName = petName;
        this.vetName = vetName;
        this.reason = reason;
        this.cost = cost;
    }

    public Appointment() {
        this.petName = "Unknown";
        this.vetName = "General";
        this.reason = "Checkup";
        this.cost = 0.0;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getVetName() {
        return vetName;
    }

    public void setVetName(String vetName) {
        this.vetName = vetName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public void applyEmergencySurcharge() {
        this.cost += 5000.0;
    }

    public void updateReason(String newReason) {
        this.reason = newReason;
    }

    @Override
    public String toString() {
        return "Appointment{petName='" + petName + "', vetName='" + vetName + "', reason='" + reason + "', cost=" + cost + "}";
    }
}
