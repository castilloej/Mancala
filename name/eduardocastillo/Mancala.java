package name.eduardocastillo;

import java.lang.*;

class Mancala {

        private Integer[] playerOneBoard, playerTwoBoard;

        private boolean IsPlayerOne = true;

        Mancala(){

                playerOneBoard = new Integer[7];
                playerTwoBoard = new Integer[7];

                for(int i = 0; i < 6; i++){
                        playerOneBoard[i] = 4;
                        playerTwoBoard[i] = 4;
                }

                playerOneBoard[6] = 0;
                playerTwoBoard[6] = 0;

        }

        private String zeroPrefix(int beadCount){
                String strBeadCount = Integer.toString(beadCount);
                return (strBeadCount.length() == 1) ? ("0" + strBeadCount) : strBeadCount;
        }

        private Integer[] getBoard(boolean _IsPlayerOne){
                return (_IsPlayerOne) ? playerOneBoard : playerTwoBoard;
        }

        private void printPlayer(boolean _IsPlayerOne){

                Integer[] currentPlayer = getBoard(_IsPlayerOne);

                for(int i = 0; i < 6; i++){
                        System.out.print(" " + zeroPrefix(currentPlayer[i]) + " ||");
                }

        }

        public void printBoard(){

                //      -----------------------------------------
                //   ----------------------------------------------
                // ||    || 04 || 04 || 04 || 04 || 04 || 04 ||    ||
                // || 48 ||----------------------------------|| 48 ||
                // ||    || 04 || 04 || 04 || 04 || 04 || 04 ||    ||
                //   -----------------------------------------------
                //      -----------------------------------------

                System.out.println("     -----------------------------------------    ");
                System.out.println("  ---------------------->>----------------------  ");

                System.out.print("||    ||");
                printPlayer(false);
                System.out.print("    ||\n");

                System.out.println("|| " + zeroPrefix(playerTwoBoard[6]) + " ||----------------------------------|| " + zeroPrefix(playerOneBoard[6]) + " ||");

                System.out.print("||    ||");
                printPlayer(false);
                System.out.print("    ||\n");

                System.out.println("  ----------------------------------------------  ");
                System.out.println("     -------------------<<--------------------    \n");
        }

        public static void main(String[] args){

                System.out.println("Welcome to Eduardo's Mancala!\n");

                Mancala newGame = new Mancala();

                newGame.printBoard();


        }
}
