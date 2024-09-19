import java.util.ArrayList;

public class Shop implements ExchangeFeatures {
    private Player player;
    private Menu menu;

    ArrayList<String> item = new ArrayList<>();

    public Shop(Player player) {
        this.player = player;
        this.menu = new Menu();
        item.add("1. 40 Gold -- Strenght Amulett: +10 DMG");
        item.add("2. 50 Gold -- Health Amulett: +100 HP");
        item.add("3. 20 Gold -- Toughness Amulet: +10 DEF");
        item.add("4. Exit Shop");
    }

    public void showShop() { // Print out items in shop.
        System.out.println("____________________________");
        System.out.println("             Shop           ");
        System.out.println("____________________________");
        for (int i = 0; i < item.size(); i++) {

            System.out.println(item.get(i));
            System.out.println("____________________________");
        }
    }
    @Override
    public void makeTransaction() {
        boolean doneShop = false;
        while (!doneShop) {
            showShop();

            int userOption = menu.playerMenuChoise();

            switch (userOption) {
                case 1 -> {
                    int price = 40;
                    if (gotEnoughGold(player.getGold(), price)) {
                        player.setGold(player.getGold() - price);
                        player.setStrength(10);
                        System.out.println("You bought a strenght amulet! Strenght + 10");
                    } else {
                        System.out.println("You cannot afford this item");
                    }
                }

                case 2 -> {
                    int price = 50;
                    if (gotEnoughGold(player.getGold(), price)) {
                        player.setGold(player.getGold() - price);
                        player.setMaximumHealth(100);
                        System.out.println("You bought a health amulet! Health + 100");
                    } else {
                        System.out.println("You cannot afford this item");
                    }
                }
                case 3 -> {
                    int price = 20;
                    if (gotEnoughGold(player.getGold(), price)) {
                        player.setGold(player.getGold() - price);
                        player.setToughness(10);
                        System.out.println("You bought a toughness amulet! Toughness + 10");
                    } else {
                        System.out.println("You cannot afford this item");
                    }
                }
                case 4 -> doneShop = true;

            }
        }
    }


    @Override
    public boolean gotEnoughGold(int gold, int price) {
        return gold >= price;
    }
}


