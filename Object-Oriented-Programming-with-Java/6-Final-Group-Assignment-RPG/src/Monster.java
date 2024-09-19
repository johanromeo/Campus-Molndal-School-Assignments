public abstract class Monster implements GameFeatures {
    private String name;
    private int currentHealth;
    private int maximumHealth;
    private int xp;

    private int gold;
    private int toughness;
    private int strength;

    public Monster(String name, int currentHealth, int maximumHealth, int xp, int gold, int toughness, int strength) {
        this.name = name;
        this.currentHealth = currentHealth;
        this.maximumHealth = maximumHealth;
        this.xp = xp;
        this.gold = gold;
        this.toughness = toughness;
        this.strength = strength;
    }

    public void avadaKadavra(Player player) {
        System.out.println("AVAAAADAAAA KADAVRA");
        System.out.println("Green lightning beam hits you....Your eyes darken...You are dead");
        player.setCurrentHealth(0);
        player.isDead(player.getCurrentHealth());
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
    public boolean isDead(int currentHealth) { // Tar emot monstrets nuvarande liv och ser om det lever eller ej
        return currentHealth <= 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentHealth() {
        if (currentHealth > 0)
            return currentHealth;
        else
            return 0;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMaximumHealth() {
        return maximumHealth;
    }

    public void setMaximumHealth(int maximumHealth) {
        this.maximumHealth = maximumHealth;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
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
        this.toughness = toughness;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
