import java.util.Scanner;

public class Arena {
    private Player player;
    private StoryTelling output;
    private CreateArenaMonster createArenaMonster;
    private Monster monster;

    public Arena() {
        this.player = new Player();
        this.output = new StoryTelling();
        this.createArenaMonster = new CreateArenaMonster();
    }

    public void initializeProgram() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        player.setName(input.nextLine());
    }

    public void findMonster() {
        int randomNum = (int) (Math.random() * 101);

        if (randomNum >= 90) {
            System.out.println("No Monsters found!");
        } else {
            enterArena();
        }
    }

    public void enterArena() {
        try {
            this.monster = createArenaMonster.createMonster();
            System.out.println("Ooohh a wild " + monster.getName() + " is ready to fight");
            monsterFight();
        } catch (Exception e) {
            System.out.println("No such monster exists...yet");
        }
    }

    public void monsterFight() {
        while (true) {
            playerVsMonsterAttacks();

            if (monster.isDead(monster.getCurrentHealth())) {
                player.lootGold(monster.getGold());
                player.levelUp(monster.getXp());
                break;
            }
            if (player.isDead(player.getCurrentHealth())) {
                System.out.println("You died! Game is now ending...");
                System.exit(0);
            }
            battleCheck();
        }
    }

    public void playerVsMonsterAttacks() {
        System.out.println("You hit" + monster.getName() + ", dealing " + player.attack() + " damage");
        monster.defend(player.attack());
        System.out.println(monster.getName() + " hit you, dealing " + monster.attack() + " damage");
        player.defend(monster.attack());
        output.statsInfo(player, monster);
    }

    private void battleCheck() {
        Scanner input = new Scanner(System.in);
        String battleCheck = "";
        while (!battleCheck.equalsIgnoreCase("j")) {
            try {
                System.out.println("[Press 'j' to continue]");
                battleCheck = input.nextLine();
            } catch (Exception e) {
                System.out.println("Press \"j\" to continue. . .");
            }
        }
    }

    public void showPlayerStats() {
        System.out.println(player);
    }

    public Player getPlayer() {
        return player;
    }
}





















