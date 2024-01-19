package org.example.dataoperations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.example.model.Player;
import org.example.model.PlayerRole;
import org.example.model.Team;

public class TeamOperation {

    static Map<Team,ArrayList<Player>> teamMap = new HashMap<>();
    static ArrayList<Player> playerList = new ArrayList<>();

    public static void loadPlayerData() {
        List<String[]> csvData = new ArrayList<>();
        for(String[] str : csvData) {
            PlayerRole role = PlayerRole.valueOf(str[2]);
            Team playerTeam = Team.valueOf(str[1]);
            Player ply = new Player(str[0],
                                    role,
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
    }

    public static List<Player> bowlerWithWicketsMoreThanN(Team team,int n) {
        List<Player> bowlerList = new ArrayList<>();
        bowlerList = teamMap.get(team).stream().filter(ply -> (ply.getPlayerWickets()>=n)).toList();
        return bowlerList;
    }

    public static List<Player> highestWicketRun(Team team) {
        List<Player> highestPlayer = new ArrayList<>();
        Player highestWicketTaker = Collections.max(teamMap.get(team),Comparator.comparing(ply -> ply.getPlayerWickets()));
        Player highestRunScorer = Collections.max(teamMap.get(team),Comparator.comparing(ply -> ply.getPlayerRuns()));
        highestPlayer.add(highestWicketTaker);
        highestPlayer.add(highestRunScorer);
        return highestPlayer;
    }

    public static List<Player> getTopNWicketTaker(int n) {
        List<Player> wicketTakerList = findTopN(playerList,n,Comparator.comparingInt(Player::getPlayerWickets));
        return wicketTakerList;
    }

    public static List<Player> getTopNRunScorer(int n) {
        List<Player> RunScorerList = findTopN(playerList,n,Comparator.comparingInt(Player::getPlayerRuns));
        return RunScorerList;
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
