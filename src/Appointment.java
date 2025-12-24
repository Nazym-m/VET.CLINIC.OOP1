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

    public void applyEmergencySurcharge() {
        this.cost += 5000.0; 
    }

    public void updateReason(String newReason) {
        this.reason = newReason;
    }

    @Override
    public String toString() {
        return "Appointment{pet='" + petName + "', vet='" + vetName + "', reason='" + reason + "', cost=" + cost + " KZT}";
    }
}
