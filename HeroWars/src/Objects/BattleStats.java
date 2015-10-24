package Objects;

/**
 * Created by Роман on 18.10.2015.
 */
public class BattleStats{

    private static int stepCount = 0;
    private int currentHP1Player;
    private int currentHP2Player;
    private int currentMP1Player;
    private int currentMP2Player;
    private String selectedAction1PLayer;
    private String selectedAction2PLayer;
    private String currentAction1PLayer;
    private String currentdAction2PLayer;

    public BattleStats(int currentHP1Player, int currentHP2Player,
                       int currentMP1Player, int currentMP2Player,
                       String selectedAction1PLayer, String selectedAction2PLayer,
                       String currentAction1PLayer, String currentdAction2PLayer) {

        this.currentHP1Player = currentHP1Player;
        this.currentHP2Player = currentHP2Player;
        this.currentMP1Player = currentMP1Player;
        this.selectedAction1PLayer = selectedAction1PLayer;
        this.currentMP2Player = currentMP2Player;
        this.selectedAction2PLayer = selectedAction2PLayer;
        this.currentAction1PLayer = currentAction1PLayer;
        this.currentdAction2PLayer = currentdAction2PLayer;
    }
    public int getStepCount() {return stepCount;}
    public void setStepCount(int stepCount) {this.stepCount = stepCount;}

    public int getCurrentMP1Player() {return currentMP1Player;}
    public int getCurrentHP1Player() {return currentHP1Player;}
    public int getCurrentHP2Player() {return currentHP2Player;}
    public int getCurrentMP2Player() {return currentMP2Player;}
    public String getSelectedAction1PLayer() {return selectedAction1PLayer;}
    public String getSelectedAction2PLayer() {return selectedAction2PLayer;}
    public String getCurrentAction1PLayer() {return currentAction1PLayer;}
    public String getCurrentdAction2PLayer() {return currentdAction2PLayer;}
}