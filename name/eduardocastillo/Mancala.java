package name.eduardocastillo;

import java.lang.*;
import java.util.*;

class Mancala {

        private Integer[] board;

        private boolean IsPlayerOne = true;
        private boolean _IsGameOver = false;

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

                if(IsGameOver()){return false;}
                if(location < 0 || location > 5){return false;}

                location = (IsPlayerOne) ? location : (5 - location);

                int prefix = (IsPlayerOne) ? 0 : 7;
                int startAt = prefix + location;
                int beads = board[startAt];

                if(beads == 0){return false;}

                board[startAt] = 0;
                startAt++;

                for(int i = 0; i < beads; i++){

                        if(!(
                                (IsPlayerOne && startAt == 13) ||
                                (!IsPlayerOne && startAt == 6)
                        )){
                                board[startAt] += 1;
                        }else{
                                i--;
                        }

                        startAt++;
                        if(startAt == 14){startAt = 0;}
                }

                int currentIndex = startAt - 1;
                int oppositeIndex = 12 - currentIndex;

                if(/* beads >= 6 && */ currentIndex != -1 && currentIndex != 6 && board[currentIndex] == 1 &&
                        (
                                (IsPlayerOne && currentIndex >= 0 && currentIndex <= 5) ||
                                (!IsPlayerOne && currentIndex >= 7 && currentIndex <= 12)
                        )
                ){
                        board[currentIndex] += board[oppositeIndex];
                        board[oppositeIndex] = 0;
                }

                if(
                        !((IsPlayerOne && (startAt == 7)) ||
                        (!IsPlayerOne && (startAt == 0)))
                ){
                        IsPlayerOne = !IsPlayerOne;
                }

                if(gatherAllBeads(true) == 0){
                        for(int i = 7; i <= 12; i++){
                                board[13] += board[i];
                                board[i] = 0;
                        }

                        _IsGameOver = true;

                } else if(gatherAllBeads(false) == 0){
                        for(int i = 0; i <= 5; i++){
                                board[6] += board[i];
                                board[i] = 0;
                        }

                        _IsGameOver = true;
                }

                return true;

        }

        private String zeroPrefix(int beadCount){

                String strBeadCount = Integer.toString(beadCount);
                return (strBeadCount.length() == 1) ? ("0" + strBeadCount) : strBeadCount;
        }

        public String status(){
                if(!IsGameOver()){return "Game is on, it's " + whoseTurn();}
                if(board[6] == board[12]){return "It's a tie!";}
                return (board[6] > board[13]) ? "Player One Won!" : "Player Two Won!";
        }

        public String whoseTurn(){
                return (IsPlayerOne) ? "Player One's Turn" : "Player Two's Turn";
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

                System.out.println(status());

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
                return _IsGameOver;
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
