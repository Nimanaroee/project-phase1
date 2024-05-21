package Model;

public class Card {
    String name;
    int attack, duration, damage, upgradeLevel, upgradeCoast;

    public Card(String name, int attack, int duration, int damage, int upgradeLevel, int upgradeCoast) {
        this.name = name;
        this.attack = attack;
        this.duration = duration;
        this.damage = damage;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCoast = upgradeCoast;
    }

    public static boolean validDefenceAttack(int attack) {
        return (10 <= attack && attack <= 100);
    }
    public static boolean validDuration(int duration) {
        return (1 <= duration && duration <= 5);
    }
    public static boolean validDamage(int damage) {
        return (10 <= damage && damage <= 50);
    }
    public void setName(String name) {
        this.name = name;
        Data.updateCard(this);
    }
    public void setAttack(int attack) {
        this.attack = attack;
        Data.updateCard(this);
    }
    public void setDuration(int duration) {
        this.duration = duration;
        Data.updateCard(this);
    }
    public void setDamage(int damage) {
        this.damage = damage;
        Data.updateCard(this);
    }
    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
        Data.updateCard(this);
    }
    public void setUpgradeCoast(int upgradeCoast) {
        this.upgradeCoast = upgradeCoast;
        Data.updateCard(this);
    }
    public String getName() {
        return this.name;
    }
    public int getAttack() {
        return attack;
    }
    public int getDamage() {
        return damage;
    }
    public int getDuration() {
        return duration;
    }
    public int getUpgradeCoast() {
        return upgradeCoast;
    }
    public int getUpgradeLevel() {
        return upgradeLevel;
    }

}
