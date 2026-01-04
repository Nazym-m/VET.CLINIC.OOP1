public class Owner {
    private int ownerId;
    private String name;
    private String phone;

    public Owner(int ownerId, String name, String phone) {
        this.ownerId = ownerId;
        setName(name);
        setPhone(phone);
    }

    public int getOwnerId() { return ownerId; }
    public String getName() { return name; }
    public String getPhone() { return phone; }

    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        } else {
            this.name = "Unknown";
        }
    }

    public void setPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            this.phone = phone;
        } else {
            this.phone = "No phone";
        }
    }

    @Override
    public String toString() {
        return "Owner ID: " + ownerId +
                ", Name: " + name +
                ", Phone: " + phone;
    }
}
