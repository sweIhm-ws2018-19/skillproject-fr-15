package alexasescape.constants;

import alexasescape.model.Item;

import java.util.*;

public final class Items {

    private static final String[] IRRELEVANT_1 = new String[]{"eine Metalltuer",
            "ein Fenster",
            "eine schwarze Tuer",
            "ein Tisch",
            "ein Tisch",
            "ein Fernseher"};
    private static final String[] IRRELEVANT_2 = new String[]{"Die Tuer ist verschlossen. ",
            "Das Fenster ist vergittert. ",
            "Die Tuer ist verschlossen. ",
            "Auf dem Tisch steht ein Laptop. ",
            "Auf dem Tisch liegt ein Buch. 1984 von George Orwell. ",
            "Es laufen gerade Nachrichtetn. "};


    private static final String[] GEGENSTAENDE = new String[]{"ein Zettel",
            "ein Laptop",
            "ein Apfel",
            "ein Schraubenschluessel",
            "ein Buch",
            "ein Handy"};


    private static final String[] CONTAINER_1 = new String[]{"ein Schrank",
            "eine Truhe",
            "eine Kiste",
            "ein Regal",
            "ein Muelleimer",
            "Ein Oelfass"};
    private static final String[] CONTAINER_2 = new String[]{"Im Schrank liegt ",
            "In der Truhe befindet sich ",
            "In der Kiste ist ",
            "Im Regal ist ",
            "Im Muelleimer liegt ",
            "In dem Oelfass liegt "};


    private static final String[] EXIT_POINTS = new String[]{"eine Stahltuer",
            //"eine Stahltuer",
            "ein Lueftungsschacht",
            "ein Spiegel",
            "eine Holztuer",
            "eine blaue Tuer",};
    private static final String[] EXIT_POINTS_DES = new String[]{"Die Tuer ist verschlossen. ",
            //"Die Tuer ist offen ",
            "vielleicht kann ich das Gitter mit irgendetwas abschrauben. ",
            "Der Spiegel sieht irgendwie komisch aus. Vielleicht ist dahinter ein anderer Raum. ",
            "Das Holz sieht poroes aus. ",
            "Die Tuer ist mit einem Zahlenschloss verschlossen. ",};
    private static final String[][] KEYS = new String[][]{{"ein Schluessel"},
            //{""},
            {"ein Taschenmesser", "ein Schraubenzieher"},
            {"ein Hammer", "ein grosser Stein"},
            {"eine Axt", "ein Brecheisen"},
            {"ein Zettel", "ein Blatt"}};
    private static final String[] SOLVE_DES = new String[]{"Der Schluessel passt ins Schloss. ",
            //" ",
            "Perfekt. Ich kann die Schrauben des Gitters vor dem Lueftungsschacht loesen. ",
            "Ich hab den Spiegel eingeschlagen. Dahinter ist ein anderer Raum. ",
            "Damit kann ich die Holztuer aufbrechen. ",
            "Ah, da stehen 5 Zahlen drauf. Das koennte die Kombination fuer das Zahlenschloss sein. Ja passt. "};

    public static List<Item> getItemList() {
        Set<Item> set = new HashSet<>();
        Item exitPoint = getExitPoint();

        set.add(exitPoint);
        Item keyCon = getKeyContainer(exitPoint);
        set.add(keyCon);
        set.add(getContainerWithout(keyCon));
        set.add(getIrrelevant());

        return new ArrayList<>(set);
    }

    private static Item getExitPoint() {
        final int index = randomIndexBelow(EXIT_POINTS.length);
        return new Item(EXIT_POINTS[index], EXIT_POINTS_DES[index], false);
    }

    private static Item getKeyContainer(Item exitPoint) {
        final int index = randomIndexBelow(CONTAINER_1.length);
        final int keyIndex = Arrays.asList(EXIT_POINTS).indexOf(exitPoint.getName());
        final int randomIndex = randomIndexBelow(KEYS[keyIndex].length);
        return new Item(CONTAINER_1[index], CONTAINER_2[index].concat(getItemsString(4, KEYS[keyIndex][randomIndex])), SOLVE_DES[keyIndex], true);
    }

    private static Item getContainerWithout(Item keyCon) {
        int index = randomIndexBelow(CONTAINER_1.length);
        if (CONTAINER_1[index].equals(keyCon.getName())) {
            index = (index + 1) % CONTAINER_1.length;
        }
        return new Item(CONTAINER_1[index], CONTAINER_2[index].concat(getItemsString(4)), false);
    }

    private static Item getIrrelevant() {
        final int index = randomIndexBelow(IRRELEVANT_1.length);
        return new Item(IRRELEVANT_1[index], IRRELEVANT_2[index], false);
    }

    public static int randomIndexBelow(int n) {
        return (int) (Math.random() * (n));
    }

    private static String randomFillItem() {
        return GEGENSTAENDE[randomIndexBelow(GEGENSTAENDE.length)];
    }

    public static String getItemsString(int n) {
        return getItemsString(n, randomFillItem());
    }

    public static String getItemsString(int n, String key) {
        Set<String> set = new HashSet<>();
        if (key.equals("")) {
            key = randomFillItem();
        }
        set.add(key);

        int maxSize = GEGENSTAENDE.length;
        int index = randomIndexBelow(maxSize);
        for (int i = 1; i < n; i++) {
            set.add(GEGENSTAENDE[index]);
            index = (index + 1) % maxSize;
        }

        String result = "";
        boolean first = true;
        String last = "";
        for (String element : set) {
            if (first) {
                last = element;
                first = false;
            } else {
                result = result.concat(element).concat(", ");
            }
        }
        result = result.concat("und ").concat(last).concat(". ");
        return result;
    }
}
