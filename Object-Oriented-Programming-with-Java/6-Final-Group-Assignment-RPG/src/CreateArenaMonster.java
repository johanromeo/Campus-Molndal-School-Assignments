public class CreateArenaMonster {
    private Menu menu;
    public CreateArenaMonster() {
        this.menu = new Menu();
    }

    public Monster createMonster() {
        menu.choseOpponent();
        int monsterOption = menu.playerMenuChoise();


        switch (monsterOption) {
            case 1 -> {
                return new Snake();
            }
            case 2 -> {
                return new Voldemort();
            }
        }
        return null;
    }
}






