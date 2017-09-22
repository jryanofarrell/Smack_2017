package BasicWebApp;

public class Smack implements java.io.Serializable {

    // Properties.
	private int rackID;
	private String slot0;
	private String slot1;
	private String slot2;
	private String slot3;

    // Getters.
    public int getRackID() { return rackID; }
    public String getSlot0() { return slot0; }
    public String getSlot1() { return slot1; }
    public String getSlot2() { return slot2; }
    public String getSlot3() { return slot3; }

    // Setters.
    public void setRackID(int i) { this.rackID = i; }
    public void setSlot0(String slot0) { this.slot0 = slot0; }
    public void setSlot1(String slot1) { this.slot1 = slot1; }
    public void setSlot2(String slot2) { this.slot2 = slot2; }
    public void setSlot3(String slot3) { this.slot3 = slot3; }

    // Important java.lang.Object overrides.
    /*public boolean equals(Object other) {
        return (other instanceof User) && (id != null) ? id.equals(((User) other).id) : (other == this);
    }
    public int hashCode() {
        return (id != null) ? (getClass().hashCode() + id.hashCode()) : super.hashCode();
    }
    public String toString() {
        return String.format("User[id=%d,name=%s,birthdate=%d]", id, name, birthdate);
    }*/
}
