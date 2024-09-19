public class Player implements GameFeatures {
    private String name;
    private int level;
    private int currentHealth;
    private int maximumHealth;
    private int currentXP;
    private int maximumXP;
    private int gold;
    private int toughness;
    private int strength;

    public Player() { // Konstruktorn sätter alla spelarens standardvärden vid uppstart
        this.level = 1;
        this.currentHealth = 100;
        this.maximumHealth = 100;
        this.maximumXP = 100;
        this.strength = 10;
    }

    public void levelUp(int xpGiven) {
        currentXP += xpGiven;
        if (currentXP >= maximumXP) {
            level++;
            setCurrentXP(0);
        }
    }

    public void lootGold(int monsterGold) { // Tar emot antalet guld som monster droppar och lägger in det i gold
        gold += monsterGold;
    }

    @Override
    public int attack() {
        return strength;
    }

    @Override
    public int defend(int damageTaken) {
        int effectiveDamage = damageTaken - toughness;
        if (effectiveDamage < 0) {
            System.out.println("No damage taken.");
            return currentHealth;
        } else {
            System.out.println(toughness + " damage blocked");
            currentHealth -= effectiveDamage;
            return currentHealth;
        }
    }

    @Override
    public boolean isDead(int currentHealth) { // Tar emot players nuvarande liv och ser om han lever eller ej. Ställer till default values om player dör
        return currentHealth <= 0;
    }

    @Override
    public String toString() { // Skriver ut alla stats om våran spelare
        return " *****************" +
                "\n * Name: " + getName() +
                "\n * Level: " + getLevel() +
                "\n * Hp: " + getCurrentHealth() + "/" + getMaximumHealth() +
                "\n * Exp: " + getCurrentXP() + "/" + getMaximumXP() +
                "\n * Gold: " + getGold() +
                "\n * Strength: " + getStrength() +
                "\n * Toughness: " + getToughness() +
                "\n *****************";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getCurrentHealth() {
        return Math.max(currentHealth, 0);
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(int maximumHealth) {
        this.maximumHealth += maximumHealth;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public void setCurrentXP(int currentXP) {
        this.currentXP = currentXP;
    }

    public int getMaximumXP() {
        return maximumXP;
    }

    public void setMaximumXP(int maximumXP) {
        this.maximumXP = maximumXP;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getToughness() {
        return toughness;
    }

    public void setToughness(int toughness) {
        this.toughness += toughness;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength += strength;
    }

}

