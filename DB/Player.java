package DB;

public class Player {
	private int id;
    private String name;
    private int lv;
    private int maxHp;
    private int hp;
    private int str;
    private int exp;
    private int pickLv;
    private int weaponLv;
    private int upgradeLv;
    private int story;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getStr() {
        return str;
    }

    public void setStr(int str) {
        this.str = str;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getPickLv() {
        return pickLv;
    }

    public void setPickLv(int pickLv) {
        this.pickLv = pickLv;
    }

    public int getWeaponLv() {
        return weaponLv;
    }

    public void setWeaponLv(int weaponLv) {
        this.weaponLv = weaponLv;
    }
    
    public int getupgradeLv() {
        return upgradeLv;
    }

    public void setupgradeLv(int upgradeLv) {
        this.upgradeLv = upgradeLv;
    }
}
