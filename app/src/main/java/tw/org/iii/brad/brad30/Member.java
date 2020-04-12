package tw.org.iii.brad.brad30;

public class Member {
    private String name;
    private boolean isVIP;
    public Member(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setVIP(boolean isVIP){
        this.isVIP = isVIP;
    }
    public boolean getVIP(){
        return isVIP;
    }
}
