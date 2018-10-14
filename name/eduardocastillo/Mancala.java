package name.eduardocastillo;

import java.lang.*;
import java.util.*;

class Mancala {

        private Integer[] board, playerOneBoard, playerTwoBoard;

        private boolean IsPlayerOne = true;

        Mancala(){

                board = new Integer[14];

                for(int i = 0; i < 14; i++){
                        board[i] = 4;
                }

                board[6]  = 0;
                board[13] = 0;

        }

        public int gatherAllBeads(boolean _IsPlayerOne){

                int totalCount = 0;

                for(int i = 0; i < 6; i++){
                        int prefix = (_IsPlayerOne) ? 0 : 7;
                        totalCount += board[prefix + i];
                }

                return totalCount;
        }

        public boolean move(int location){

                if(location < 0 || location > 5){return false;}

                location = (IsPlayerOne) ? location : (5 - location);

                int prefix = (IsPlayerOne) ? 0 : 7;
                int startAt = prefix + location;
                int beads = board[startAt];

                if(beads == 0){return false;}

                board[startAt] = 0;
                startAt++;

                for(int i = 0; i < beads; i++){
                        board[startAt] += 1;
                        startAt++;

                        if(startAt == 14){startAt = 0;}
                }

                IsPlayerOne = !IsPlayerOne;

                return true;

        }

        private String zeroPrefix(int beadCount){

                String strBeadCount = Integer.toString(beadCount);
                return (strBeadCount.length() == 1) ? ("0" + strBeadCount) : strBeadCount;
        }

        private void printPlayer(boolean _IsPlayerOne){

                int prefix = (_IsPlayerOne) ? 0 : 7;

                if(_IsPlayerOne){
                        for(int i = 0; i < 6; i++){
                                System.out.print(" " + zeroPrefix(board[i]) + " ||");
                        }
                }else{
                        for(int i = 12; i != 6; i--){
                                System.out.print(" " + zeroPrefix(board[i]) + " ||");
                        }
                }

        }

        public void printBoard(){

                // Example:
                //      -----------------------------------------
                //   ---------------------->>----------------------
                // ||    || 04 || 04 || 04 || 04 || 04 || 04 ||    ||
                // || 48 ||----------------------------------|| 48 ||
                // ||    || 04 || 04 || 04 || 04 || 04 || 04 ||    ||
                //   ----------------------<<----------------------
                //      -----------------------------------------

                System.out.println("     -----------------------------------------    ");
                System.out.println("  ---------------------->>----------------------  ");

                System.out.print("||    ||");
                printPlayer(true);
                System.out.print("    ||\n");

                System.out.println("|| " + zeroPrefix(board[13]) + " ||----------------------------------|| " + zeroPrefix(board[6]) + " ||");

                System.out.print("||    ||");
                printPlayer(false);
                System.out.print("    ||\n");

                System.out.println("  ----------------------<<----------------------  ");
                System.out.println("     -----------------------------------------    \n");

        }

        public boolean IsGameOver(){
                return false;
        }

        public static void main(String[] args){

                System.out.println("Welcome to Eduardo's Mancala!\n");

                Mancala newGame = new Mancala();
                Scanner scanner = new Scanner(System.in);

                newGame.printBoard();

                while(!newGame.IsGameOver()){
                        int newMove = scanner.nextInt();
                        newGame.move(newMove);
                        newGame.printBoard();
                }

                newGame.printBoard();

        }
}
