package Model;

public class CardModel {
    String name;
    int defence;
    int duration;
    int damage;
    int upgradeLevel;
    int upgradeCost;
    int price;


    public CardModel() {

    }

    public CardModel(String name, int defence, int duration, int damage, int upgradeLevel, int upgradeCoast) {
        this.name = name;
        this.defence = defence;
        this.duration = duration;
        this.damage = damage;
        this.upgradeLevel = upgradeLevel;
        this.upgradeCost = upgradeCoast;
    }

    public static boolean validCard(int attack, int duration, int damage) {
        return (validDefenceAttack(attack) & validDuration(duration) & validDamage(damage));
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

    public void setUpgradeCoast(int upgradeCost) {
        this.upgradeCost = upgradeCost;
        Data.updateCard(this);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        Data.updateCard(this);
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
        Data.updateCard(this);
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
        Data.updateCard(this);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
        Data.updateCard(this);
    }

    public int getUpgradeCost() {
        return upgradeCost;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public void setUpgradeLevel(int upgradeLevel) {
        this.upgradeLevel = upgradeLevel;
        Data.updateCard(this);
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        Data.updateCard(this);
    }

    public void upgrade() {
        this.upgradeLevel++;
        this.damage += this.damage * 20 / 100;
        this.defence += this.defence * 20 / 100;
        this.upgradeCost += this.upgradeCost / 5;
        Data.updateCard(this);
    }

}
