package alexasescape.constants;

public final class SpeechText {
    public static final String TEST = "So klingt meine normale Stimme <emphasis level=\"strong\">Das ist jetzt langsamer und lauter</emphasis> " +
            "<emphasis level=\"reduced\">Nun spreche ich schneller und leiser</emphasis> " +
            "<voice name=\"Hans\">Hallo ich bin Hans</voice> <voice name=\"Marlene\">Hallo hier ist Marlene</voice> " +
            "<voice name=\"Vicki\">Und zu guter letzt Vicki</voice>";
    //Außerdem noch prosody anschauen! rate, pitch and volume can be altered.

    public static final String LAUNCH_GAME = "Hallo. Moechtest Du das Spiel starten oder Deinen Highscore abfragen";
    public static final String CANCEL_OR_STOP = "Ciao";
    public static final String HANG_UP = "Hallo. Moechtest Du das Spiel starten oder Deinen Highscore abfragen";
    public static final String START = "Hallo!? Wer ist da?!";
    private static final String[] ROOM_DES = new String[]{"Hier ist ", "In diesem Raum ist ", "Hier sehe ich ", "In diesem Raum sehe ich "};
    public static final String STORY_2 = "Was soll ich tuen?";
    public static final String NOT_PLAYED = "Du hast noch keine Runde gespielt. Also los gehts!";
    public static final String NOT_WON = "hast du noch keinen erfolgreich beendet!";
    public static final String WHAT_WAS_THAT = "Wie bitte?";
    public static final String GAME_OVER = "Spiel zu Ende";
    public static final String GAME_WON = "Danke! Ich bin frei! Und jetzt schalte dein Eco aus!";
    public static final String NAME_WRONG = "Ich habe Deinen Namen nicht verstanden";
    public static final String WHOS_HIGHSCORE = "Ich weiss nicht fuer wen ich den Highscore nachschlagen soll?";
    public static final String ERROR = "Error";
    public static final String NO_GAME = "There is no game";
    public static final String NO_ITEM = "This item doesnt exist";
    public static final String HELP = "Du musst Alexa helfen aus dem Haus zu entkommen. Alexa beschreibt dir in jedem Raum was sie sieht und du musst dann entscheiden was sie tun soll, um in den nächsten Raum zu kommen.";
    public static final String NOT_POSSIBLE = "Dieser Befehl steht gerade nicht zur Verfuegung";
    // Arguments: minutes, seconds
    public static final String BEST_ROUND = "liegt die beste Runde bei %d Minuten und %d Sekunden.";

    // Arguments: totalGames
    public static final String OF_TRIES = "Von insgesamt %d versuchen ";

    // Arguments: PlayerName
    public static final String STORY = "Gott sei Dank %s! Du musst mir helfen! Ich wurde entfuehrt und in irgendein Haus gesperrt! Ich bin in einem dunklen Raum. ";

    // Audios
    public static final String RINGTONE = "<audio src='soundbank://soundlibrary/ui/gameshow/amzn_ui_sfx_gameshow_bridge_02'/>";

    private SpeechText() {
        super();
    }

    public static String getRandomRoomDes(){
        return ROOM_DES[Items.randomIndexBelow(ROOM_DES.length)];
    }
}
