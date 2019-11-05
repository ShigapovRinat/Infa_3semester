package models;

public class Logout extends Payload {
    private boolean log;

    public boolean isLog() {
        return log;
    }

    public void setLog(boolean log) {
        this.log = log;
    }
}
