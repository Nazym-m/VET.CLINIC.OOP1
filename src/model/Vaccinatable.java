package model;

public interface Vaccinatable {
    boolean needsVaccination();
    void administerVaccine();
    String getVaccineInfo();
}
