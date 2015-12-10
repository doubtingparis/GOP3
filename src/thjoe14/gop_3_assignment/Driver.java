package thjoe14.gop_3_assignment;

import javafx.scene.control.TextArea;

public class Driver {

    FXMLDocumentController doc;
    int numberofPlayers;
    int currentPlayer;
    Player[] players;
    int requiredRounds = 5;//Required rounds to win game
    
    TextArea gameField = new TextArea();
    
    Dice dice = new Dice();
    
    FieldInterface[] gameBoard = new FieldInterface[40];// Gameboard

    public Driver(int numberofPlayers) {
        //Fill player array
        players = new Player[numberofPlayers];
        for (int i = 0; i < numberofPlayers; i++) {
            players[i] = new Player("Player " + (i + 1), 0, 0);
        }
    }
    
    //Fill gameBoards fields
    public void fillFields() {
        for (int i = 0; i < gameBoard.length; i++) {
            switch (i + 1) {
                //Other fields
                case 1: //Start
                case 3: //Prøv lykken
                case 8: //Prøv lykken
                case 11://Fængsel
                case 18://Prøv lykken
                case 23://Prøv lykken
                case 34://Prøv lykken
                case 37://Prøv lykken
                case 39://Skat/gevinst
                    gameBoard[i] = new OtherField(MonopolyConstants.FIELD_NAMES[i], i + 1);
                    break;
                case 31: // Moveto jail
                    gameBoard[i] = new GoToJailField(MonopolyConstants.FIELD_NAMES[i], i + 1);
                    break;
                case 6: 
                // Shipping:
                case 16:
                case 26:
                case 36:
                    gameBoard[i] = new ShippingField(50, MonopolyConstants.FIELD_NAMES[i], i + 1, 200);
                    break;
                //Breweries:
                case 13:
                case 29: 
                    gameBoard[i] = new BreweryField(dice.getDice(), MonopolyConstants.FIELD_NAMES[i], i + 1, 150);//Dice parameter, da betaling afhænger af antal øjne der er slået
                    break;
                case 5: //Betal indkomstskat
                    gameBoard[i] = new taxField(MonopolyConstants.STATETAX, MonopolyConstants.FIELD_NAMES[i], i + 1);
                case 21://Ekstraordinær statsskat
                    gameBoard[i] = new taxField(MonopolyConstants.STATETAX*2, MonopolyConstants.FIELD_NAMES[i], i + 1);
                default: // If case not caught above, use default
                    gameBoard[i] = new StreetField(3 * (i+1), MonopolyConstants.FIELD_NAMES[i], i + 1, 10 * (i+1));
                    // Afgift for at lande på feltet og pris for at købe feltet er her simuleret med hhv 3*i og 10*i.
            }       // Field(afgift,navn,fieldNumber,købspris)
        }
    }

    public void nextTurn() {
        if (++currentPlayer >= players.length) {
            currentPlayer = 0;
        }
    }

    public Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public Player getPlayer(int playerID) {
        return players[playerID];
    }

    public int getnumberofPlayers() {
        return players.length;
    }

    //Text messages
    public void metRequirementsMessage(int requiredRounds) { //text
        System.out.println(getCurrentPlayer().getName() + " is the first to finish " + requiredRounds + " rounds!");
        System.out.println("-----------------------------------------");
    }
    public void winMessage() {
        for (int k = 0; k < getnumberofPlayers(); k++) {
            System.out.println(getPlayer(k).getName() + " has completed " + getPlayer(k).getRound() + " rounds and has " + getPlayer(k).getMoney() + "$");
        }
        System.out.println("-----------------------------------------");
        System.out.println(getCurrentPlayer().getName() + " WINS!!!");
        System.out.println("-----------------------------------------");
    }
    public void standardMessage(int rollvalue) {
        System.out.println(getCurrentPlayer().getName() + " rolled " + rollvalue + " and moves to " + gameBoard[getCurrentPlayer().getPos()].getName() + "(" + (getCurrentPlayer().getPos()+1) + ")");
    }
    public void bonusMessage(int rollvalue) {
        System.out.print("The dice match, " + getCurrentPlayer().getName() + " rolls ");
        System.out.println(rollvalue + " on a bonus roll and moves to " + gameBoard[getCurrentPlayer().getPos()].getName() + "(" + (getCurrentPlayer().getPos()+1) + ")");
    }
    
    
    //RUN GAME
    public TextArea playGame() {
        
        gameField.setText("Game started!");
        System.out.println("------------");
        //Run until player reaches requiredRounds
        while (getCurrentPlayer().getRound() != requiredRounds) {
            dice.throwDice();//roll dice
            getCurrentPlayer().move(dice.getDice());//move player
            standardMessage(dice.getDice());//text message
            gameBoard[getCurrentPlayer().getPos()].consequense(getCurrentPlayer()); //Field consequence
            System.out.println(getCurrentPlayer().getName() + " has " + getCurrentPlayer().getMoney() + "$");//Debugging 
            if (getCurrentPlayer().getRound() == requiredRounds) { //If a player reaches required rounds on standard roll, exit loop
                metRequirementsMessage(requiredRounds);//text message
                break;
            }
            //Bonus diceroll
            while (dice.isMatch()) {
                dice.throwDice();
                getCurrentPlayer().move(dice.getDice());
                bonusMessage(dice.getDice());
                gameBoard[getCurrentPlayer().getPos()].consequense(getCurrentPlayer()); //Field consequence
                System.out.println(getCurrentPlayer().getName() + " has " + getCurrentPlayer().getMoney() + "$");//Debugging 
                if (getCurrentPlayer().getRound() == requiredRounds) { //If a player reaches required rounds on a bonus roll, exit loop
                    break;
                }
            }            
            //If a player has reached required rounds, exit to winMessage, otherwise next player turn
            if (getCurrentPlayer().getRound() == requiredRounds) {
                metRequirementsMessage(requiredRounds);
                break;
            } else {
                nextTurn();
            }
        }
        winMessage();
        return gameField;
    }
}
