package org.example.main;

import org.example.logging.Logger;
import org.example.model.Team;
import org.example.dataoperations.CreateFixtures;
import org.example.dataoperations.TeamOperation;
import org.example.userutility.UserInputHandler;
import org.example.userutility.UserUtilityMenu;


public class Main {
    private static final Logger loggerObj = Logger.getLoggerObject();

    private static void teamUserInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        UserInputHandler inputHandler = UserInputHandler.getUserInputHandlerObject();
        while(true) {
            int operationOp = userMenu.teamMenu();
            if(operationOp==-1) {
                break;
            }
            String teamName = inputHandler.readName("Enter Team Name : ");
            switch (operationOp) {
                case 1:
                    TeamOperation.bowlerWithWicketsMoreThanN(Team.valueOf(teamName), 40);
                    break;
                case 2:
                    TeamOperation.highestWicketRun(Team.valueOf(teamName));
                    break;
                default:
                    loggerObj.errorLog("Wrong Choice. Try Again");
                    break;
            }
        }
    }

    public static void seasonUserInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        while(true) {
            int operationOp = userMenu.seasonMenu();
            if(operationOp==-1) {
                break;
            }
            switch (operationOp) {
                case 1:
                    TeamOperation.getTopNWicketTaker(3);
                    break;
                case 2:
                    TeamOperation.getTopNRunScorer(3);
                    break;
                case 3:
                    CreateFixtures.generateMatches();
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
                    break;
            }
        }

    }

    public static void userInteraction() {
        UserUtilityMenu userMenu = UserUtilityMenu.getUserUtilityMenuObject();
        while(true) {
            int operationOp = userMenu.operationMenu();
            if(operationOp==-1) {
                break;
            }
            switch (operationOp) {
                case 1:
                    teamUserInteraction();
                    break;
                case 2:
                    seasonUserInteraction();
                    break;
                default:
                    loggerObj.errorLog("Enter valid operation number");
                    break;
            }
        }
    }

    public static void main(String[] args) {
        TeamOperation.loadPlayerData();
        userInteraction();
    }
}