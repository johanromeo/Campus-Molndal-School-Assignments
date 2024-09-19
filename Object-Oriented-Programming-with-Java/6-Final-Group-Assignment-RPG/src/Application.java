public class Application {
    private Arena arena;
    private Menu menu;
    private Shop shop;

    public Application() {
        this.arena = new Arena();
        this.menu = new Menu();
        this.shop = new Shop(arena.getPlayer());
    }

    public void startGame() {
        arena.initializeProgram();
        boolean isDone = false;

        while (!isDone && arena.getPlayer().getLevel() != 10) {
            menu.showMainMenu();
            int userOption = menu.playerMenuChoise();

            switch (userOption) {
                case 1 -> arena.findMonster();
                case 2 -> arena.showPlayerStats();
                case 3 -> shopPlayerItem();
                case 4 -> isDone = true;
            }
        }
        System.out.println("Thank you for playing. Farewell mate. . .");
    }
    private void shopPlayerItem() {
        shop.makeTransaction();
    }
}

