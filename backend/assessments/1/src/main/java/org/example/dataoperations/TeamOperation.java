package org.example.dataoperations;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.example.logging.Logger;
import org.example.model.Player;
import org.example.model.PlayerRole;
import org.example.model.Team;



public class TeamOperation {

    private TeamOperation() {}

    private static final Path IPL_CSV_FILE_PATH = Path.of(
            "src/main/resources/IPL_2021-data.csv");

    static Map<Team,ArrayList<Player>> teamMap = new HashMap<>();
    static ArrayList<Player> playerList = new ArrayList<>();

    private static PlayerRole getEnumRole(String str) {
        if(str.equals("WICKET KEEPER")) return PlayerRole.WICKET_KEEPER;
        else if(str.equals("ALL ROUNDER")) return PlayerRole.ALL_ROUNDER;
        return PlayerRole.valueOf(str);
    }

    public static void loadPlayerData() {
        for(Team tm : Team.values()) {
            teamMap.put(tm, new ArrayList<>());
        }
        Logger loggerObj = Logger.getLoggerObject();
        List<String[]> csvData = LoadData.parseCSV(IPL_CSV_FILE_PATH);
        for(String[] str : csvData) {

            

            Team playerTeam = Team.valueOf(str[1]);
            Player ply = new Player(str[0],
                                    getEnumRole(str[2]),
                                    Integer.parseInt(str[3]),
                                    Integer.parseInt(str[4]),
                                    Float.parseFloat(str[5]),
                                    Float.parseFloat(str[6]),
                                    Integer.parseInt(str[7]),
                                    playerTeam
                        );
            teamMap.get(playerTeam).add(ply);
            playerList.add(ply);
        }
        loggerObj.debugLog("CSV data loaded successfully");
    }


    public static void printPlayerDetails(List<Player> playerList) {
        Logger loggerObj = Logger.getLoggerObject();
        for(Player ply : playerList) {
            loggerObj.infoLog(ply.toString());
        }
    }

    public static List<Player> bowlerWithWicketsMoreThanN(Team team,int n) {
        List<Player> bowlerList;
        bowlerList = teamMap.get(team).stream().filter(ply -> (ply.getPlayerWickets()>=n)).toList();
        printPlayerDetails(bowlerList);
        return bowlerList;
    }

    public static List<Player> highestWicketRun(Team team) {
        List<Player> highestPlayer = new ArrayList<>();
        Player highestWicketTaker = Collections.max(teamMap.get(team),Comparator.comparing(Player::getPlayerWickets));
        Player highestRunScorer = Collections.max(teamMap.get(team),Comparator.comparing(Player::getPlayerRuns));
        highestPlayer.add(highestWicketTaker);
        highestPlayer.add(highestRunScorer);
        printPlayerDetails(highestPlayer);
        return highestPlayer;
    }

    public static List<Player> getTopNWicketTaker(int n) {
        List<Player> wicketTakerList = findTopN(playerList,n,Comparator.comparingInt(Player::getPlayerWickets));
        printPlayerDetails(wicketTakerList);
        return wicketTakerList;
    }

    public static List<Player> getTopNRunScorer(int n) {
        List<Player> runScorerList = findTopN(playerList,n,Comparator.comparingInt(Player::getPlayerRuns));
        printPlayerDetails(runScorerList);
        return runScorerList;
    }

    private static List<Player> findTopN(List<Player> playerList, int n, Comparator<Player> comparator) {
        PriorityQueue<Player> priorityQueue = new PriorityQueue<>(comparator);
        for (Player ply : playerList) {
            priorityQueue.offer(ply);
            if (priorityQueue.size() > n) {
                priorityQueue.poll();
            }
        }
        return new ArrayList<>(priorityQueue);
    }
}
