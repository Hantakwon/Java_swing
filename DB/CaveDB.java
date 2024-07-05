package DB;

public class CaveDB {
    private int id;
    private int cavegrade;
    private int cavemaxgrade;
    private int caveexp;
    private int oremaxhp;
    private int orehp;
    private int monstermaxhp;
    private int monsterhp;
    private int division;
    
    // id에 대한 get 메서드
    public int getId() {
        return id;
    }

    // id에 대한 set 메서드
    public void setId(int id) {
        this.id = id;
    }

    // cavegrade에 대한 get 메서드
    public int getCavegrade() {
        return cavegrade;
    }

    // cavegrade에 대한 set 메서드
    public void setCavegrade(int cavegrade) {
        this.cavegrade = cavegrade;
    }

    // cavegrade에 대한 get 메서드
    public int getCaveMaxgrade() {
        return cavemaxgrade;
    }

    // cavegrade에 대한 set 메서드
    public void setCaveMaxgrade(int cavemaxgrade) {
        this.cavemaxgrade = cavemaxgrade;
    }
    
    // caveexp에 대한 get 메서드
    public int getCaveexp() {
        return caveexp;
    }

    // caveexp에 대한 set 메서드
    public void setCaveexp(int caveexp) {
        this.caveexp = caveexp;
    }

    // oremaxhp에 대한 get 메서드
    public int getOremaxhp() {
        return oremaxhp;
    }

    // oremaxhp에 대한 set 메서드
    public void setOremaxhp(int oremaxhp) {
        this.oremaxhp = oremaxhp;
    }

    // orehp에 대한 get 메서드
    public int getOrehp() {
        return orehp;
    }

    // orehp에 대한 set 메서드
    public void setOrehp(int orehp) {
        this.orehp = orehp;
    }

    // monstermaxhp에 대한 get 메서드
    public int getMonstermaxhp() {
        return monstermaxhp;
    }

    // monstermaxhp에 대한 set 메서드
    public void setMonstermaxhp(int monstermaxhp) {
        this.monstermaxhp = monstermaxhp;
    }

    // monsterhp에 대한 get 메서드
    public int getMonsterhp() {
        return monsterhp;
    }

    // monsterhp에 대한 set 메서드
    public void setMonsterhp(int monsterhp) {
        this.monsterhp = monsterhp;
    }
    
    public int getDivision() {
        return division;
    }

    public void setDivision(int division) {
        this.division = division;
    }
}
