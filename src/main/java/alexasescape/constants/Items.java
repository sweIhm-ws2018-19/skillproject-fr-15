package alexasescape.constants;

import alexasescape.model.Item;

import java.util.*;

public final class Items {

    private Items() {
    }

    private static final Random RANDOM = new Random(System.currentTimeMillis());

    private static final String DIE_TUER_IST_VERSCHLOSSEN_SONAR_STRING = "Die Tuer ist verschlossen. ";

    // Namen der irrelevanten Items für Räume
    private static final String[] IRRELEVANT_1 = {"eine Metalltuer",
            "ein Fenster",
            "eine schwarze Tuer",
            "ein Tisch",
            "ein Tisch",
            "ein Fernseher",
			"ein Stuhl"};
    // Beschreibungen der irrelevanten Items - Beschreibug an der Stelle i im Array gehört zum Namen an der Stelle i
    private static final String[] IRRELEVANT_2 = {
            DIE_TUER_IST_VERSCHLOSSEN_SONAR_STRING,
            "Das Fenster ist vergittert. ",
            DIE_TUER_IST_VERSCHLOSSEN_SONAR_STRING,
            "Auf dem Tisch steht ein Laptop. ",
            "Auf dem Tisch liegt ein Buch. 1984 von George Orwell. ",
            "Es laufen gerade Nachrichten. ",
			"Der Stuhl ist leer"};


    // Gegenstände die in die Items kommen
    private static final String[] GEGENSTAENDE = {"ein Zettel",
            "ein Laptop",
            "ein Apfel",
            "ein Schraubenschluessel",
            "ein Buch",
            "ein Handy",
            "eine Tasse",
            "ein Eco Dot",
			"ein Stift",
			"ein Teller",
			"eine Flasche",
			"ein Becher"};


    // Namen der Items für Räume
    private static final String[] CONTAINER_1 = {"ein Schrank",
            "eine Truhe",
            "eine Kiste",
            "ein Regal",
            "ein Muelleimer",
            "Ein Oelfass",
			"Ein Karton",
			"Eine Vase"};
    // Beschreibungen der Items - Beschreibug an der Stelle i im Array gehört zum Namen an der Stelle i
    private static final String[] CONTAINER_2 = {Audio.WALKING.concat("Im Schrank liegt "),
            Audio.TRUHE.concat(" In der Truhe befindet sich "),
            "In der Kiste ist ",
            Audio.WALKING.concat("Im Regal ist "),
            "Im Muelleimer liegt ",
            Audio.WALKING.concat("In dem Oelfass liegt "),
			"Im Karton ist ",
			"In der Vase ist "};


    // Namen der Ausgangspunkte
    private static final String[] EXIT_POINTS = {"eine Stahltuer",
            "ein Lueftungsschacht",
            "ein Spiegel",
            "eine Holztuer",
            "eine Tuer",};
    // Beschreibungen der Ausgangspunkte
    private static final String[] EXIT_POINTS_DES = {
            DIE_TUER_IST_VERSCHLOSSEN_SONAR_STRING,
            "vielleicht kann ich das Gitter mit irgendetwas abschrauben. ",
            "Der Spiegel sieht irgendwie komisch aus. Vielleicht ist dahinter ein anderer Raum. ",
            "Das Holz sieht poroes aus. ",
            "Die Tuer ist mit einem Zahlenschloss verschlossen. ",};
    // Namen der Gegenstände die in Items kommen und Schlüssel sind. 2 Dimensionales Array! Array an der Stelle i im
    // Array enthält mögliche Shlüssel für den zugehörigen Ausgangspunkt (Es wird einer zufällig gewählt)
    private static final String[][] KEYS = {{"ein Schluessel"},
            {"ein Taschenmesser", "ein Schraubenzieher"},
            {"ein Hammer", "ein grosser Stein"},
            {"eine Axt", "ein Brecheisen"},
            {"ein Zettel", "ein Blatt"}};
    // Beschreibung, wie Alexa den Raum verlassen konnte.
    private static final String[] SOLVE_DES = {"Der Schluessel passt ins Schloss. " + Audio.DOOR_1,
            "Mal schauen ob ich damit die Schrauben des Gitters vor dem Lueftungsschacht loesen kann. " + Audio.BREAK_2_S + " Ja, funktioniert. " + Audio.BREAK_2_S + " Mal schauen wo mich der Schacht hinfuehrt " + Audio.SCHACHT,
            Audio.SPIEGEL + " Ich hab den Spiegel eingeschlagen. Dahinter ist ein anderer Raum. ",
            "Damit kann ich vielleicht die Holztuer aufbrechen. " + Audio.HOLZTUER + " Ja, hat geklappt. ",
            "Ah, da stehen 5 Zahlen drauf. Das koennte die Kombination fuer das Zahlenschloss sein. " + Audio.BREAK_2_S + " Ja passt. " + Audio.DOOR_2};

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
        return RANDOM.nextInt(n);
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
