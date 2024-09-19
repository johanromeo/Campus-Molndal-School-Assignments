public class StoryTelling {
    public void statsInfo(Player player, Monster monster) {
        System.out.println(player.getName() + " : " + player.getCurrentHealth() + " hp left");
        System.out.println(monster.getName() + " : " + monster.getCurrentHealth()+ " hp left");
    }
}
