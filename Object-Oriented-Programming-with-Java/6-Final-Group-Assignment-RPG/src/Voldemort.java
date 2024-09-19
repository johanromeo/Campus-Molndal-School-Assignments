public class Voldemort extends Monster implements GameFeatures {

    public Voldemort() {
        super("Voldemort", 250, 300, 100, 45, 10, 20);
    }

    public void avadaKadavra(Player player) {
        super.avadaKadavra(player);
    }

    @Override
    public int attack() {
        return super.attack();
    }

    @Override
    public int defend(int damageTaken) {
        return super.defend(damageTaken);
    }

    @Override
    public boolean isDead(int currentHealth) {
        return super.isDead(currentHealth);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public int getCurrentHealth() {
        return super.getCurrentHealth();
    }

    @Override
    public void setCurrentHealth(int currentHealth) {
        super.setCurrentHealth(currentHealth);
    }

    @Override
    public int getMaximumHealth() {
        return super.getMaximumHealth();
    }

    @Override
    public void setMaximumHealth(int maximumHealth) {
        super.setMaximumHealth(maximumHealth);
    }

    @Override
    public int getXp() {
        return super.getXp();
    }

    @Override
    public void setXp(int xp) {
        super.setXp(xp);
    }

    @Override
    public int getGold() {
        return super.getGold();
    }

    @Override
    public void setGold(int gold) {
        super.setGold(gold);
    }

    @Override
    public int getToughness() {
        return super.getToughness();
    }

    @Override
    public void setToughness(int toughness) {
        super.setToughness(toughness);
    }

    @Override
    public int getStrength() {
        return super.getStrength();
    }

    @Override
    public void setStrength(int strength) {
        super.setStrength(strength);
    }
}
