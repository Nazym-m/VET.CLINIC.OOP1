public class Veterinarian {
    private String name;
    private String specialization;
    private int experienceYears;
    private boolean isOnDuty;

    public Veterinarian(String name, String specialization, int experienceYears, boolean isOnDuty) {
        this.name = name;
        this.specialization = specialization;
        this.experienceYears = experienceYears;
        this.isOnDuty = isOnDuty;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int exp) { this.experienceYears = exp; }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    public void changeDutyStatus() {
        this.isOnDuty = !this.isOnDuty;
    }

    @Override
    public String toString() {
        return "Veterinarian{name='" + name + "', spec='" + specialization + "', exp=" + experienceYears + ", onDuty=" + isOnDuty + "}";
    }
}