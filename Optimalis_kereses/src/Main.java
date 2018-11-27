import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Viktor on 2017. 03. 10..
 */
public class Main {
    static int pocketHeight;
    static int pocketWidth;
    static int numberOfItems;
    static ArrayList<Item> list;
    static int[][] pocket;

    public static void init() {
        Scanner scanner = new Scanner(System.in);
        String puffer = scanner.nextLine();
        String[] stringPuffer = puffer.split("\t");
        pocketHeight = Integer.parseInt(stringPuffer[0]);
        pocketWidth = Integer.parseInt(stringPuffer[1]);
        numberOfItems = Integer.parseInt(scanner.nextLine());

        list = new ArrayList<>();

        for(int i = 0; i < numberOfItems; i++) {
            puffer = scanner.nextLine();
            stringPuffer = puffer.split("\t");
            Item item = new Item(Integer.parseInt(stringPuffer[0]), Integer.parseInt(stringPuffer[1]));
            list.add(item);
        }

        pocket = new int[pocketHeight][pocketWidth];
        /*for (int i = 0; i < pocketHeight; i++) {
            for (int j = 0; j < pocketWidth; j++) {
                pocket[i][j] = 0;
            }
        }*/

        list.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return item2.width - item1.width;
            }
        });
    }

    public static boolean isFree(int x, int y, Item item) {
        if(pocketHeight < item.height + x || pocketWidth < item.width + y)
            return false;

        for (int i = x; i < x + item.height; i++) {
            for (int j = y; j < y + item.width; j++) {
                if (pocket[i][j] != 0) return false;
            }
        }
        return true;
    }


    public static void findSpace(Item item) {
        for (int j = 0; j < pocketWidth; j++) {
            for (int i = 0; i < pocketHeight; i++) {
                if (isFree(i, j, item)) {
                    item.beginningWidth = j;
                    item.beginningHeight = i;
                    return;
                }
            }
        }

        list.remove(item);
        list.add(0, item);

        printMatrix();
        System.out.println("Magasság: " + item.height + " " + "Szélesség: " + item.width + " " +  "ID: " + item.id + "\n");
        throw new RuntimeException("bibi");
    }

    public static void allocateSpace(Item item) {

        for (int i = item.beginningHeight; i < item.beginningHeight + item.height; i++) {
            for (int j = item.beginningWidth; j < item.beginningWidth + item.width; j++) {
                pocket[i][j] = item.id;
            }
        }

    }

    public static void printMatrix() {
        for (int i = 0; i < pocketHeight; i++) {
            for (int j = 0; j < pocketWidth; j++) {
                System.out.print(pocket[i][j]);
                if (j != pocketWidth - 1)
                    System.out.print("\t");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        init();

        while(true) {
            try {
                for (Item item : list) {
                    findSpace(item);
                    allocateSpace(item);
                }
                printMatrix();
                return;
            } catch (RuntimeException e) {
                pocket = new int[pocketHeight][pocketWidth];
            }
        }

    }
}

class Item {
   int height;
   int width;
   int beginningHeight = 0;
   int beginningWidth = 0;
   static int defId = 1;
   int id;

   public Item(int h, int w) {
       height = h;
       width = w;
       id = defId;
       defId++;
   }
}
