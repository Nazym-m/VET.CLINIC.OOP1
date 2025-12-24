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

    public Veterinarian() {
        this.name = "Unknown";
        this.specialization = "General";
        this.experienceYears = 0;
        this.isOnDuty = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    public boolean isOnDuty() {
        return isOnDuty;
    }

    public void setOnDuty(boolean isOnDuty) {
        this.isOnDuty = isOnDuty;
    }

    public boolean isExperienced() {
        return experienceYears >= 5;
    }

    public void changeDutyStatus() {
        this.isOnDuty = !this.isOnDuty;
    }

    @Override
    public String toString() {
        return "Veterinarian{name='" + name + "', specialization='" + specialization + 
               "', experienceYears=" + experienceYears + ", isOnDuty=" + isOnDuty + "}";
    }
}
