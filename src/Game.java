/**
 *  Esta é a classe principal do jogo World of Zuul. 
 *  
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Cria o jogo e inicializa o mapa interno.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Cria todas as salas e liga suas saídas.
     */
    private void createRooms()
    {
        Room outside, theatre, pub, lab, office, attic;
      
        // create the rooms
        outside = new Room("fora da entrada principal da universidade");
        theatre = new Room("em um auditório");
        pub = new Room("na cantina do campus");
        lab = new Room("em um laboratório de informática");
        office = new Room("na sala dos professores");
        attic = new Room("no sótão da laboratório");
        
        // initialise room exits
        outside.setExits("leste", theatre);
        outside.setExits("sul", lab);
        outside.setExits("oeste", pub);
        theatre.setExits("oeste", outside);
        pub.setExits("leste", outside);
        lab.setExits("norte", outside);
        lab.setExits("leste", office);
        lab.setExits("cima", attic);
        attic.setExits("baixo", lab);
        office.setExits("oeste", lab);

        currentRoom = outside;  // Começa o jogo fora 
    }

    /**
     *  A rotina de jogo principal. Faz um loop até o fim do jogo.
     */
    public void play() 
    {            
        printWelcome();

        // Entra o loop principal. Aqui lemos comandos repetidamente e 
        // os executamos até que o jogo termime.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Obrigado por jogar.  Adeus.");
    }

    /**
     * Imprime a mensagem de boas vindas ao usuário.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bem-vindo ao Mundo de Zuul!");
        System.out.println("Mundo de Zuul é um jogo de aventura, incrivelmente chato.");
        System.out.println("Digite 'ajuda' se você precisar de ajuda.");
        System.out.println();
        printLocationInfo();
    }
    
    /**
     * Imptimr informações relativas à
     * Localização atual.
     */
    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Dado um comando, processa (ou seja: executa) o comando.
     * @param command O comando a ser processado.
     * @return true Se o comando finaliza o jogo, senão, falso.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("Não sei o que você quer dizer...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("ajuda"))
            printHelp();
        else if (commandWord.equals("ir_para"))
            goRoom(command);
        else if (commandWord.equals("sair"))
            wantToQuit = quit(command);
        else if (commandWord.equals("examinar"))
        	look(command);
        else if (commandWord.equals("comer"))
        	eat(command);

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Imprime informações de ajuda.
     * Aqui imprimimos ua mensagem estúpida e listamos os comandos
     * disponíveis.
     */
    private void printHelp() 
    {
        System.out.println("Você está perdido. Você está só. Você caminha");
        System.out.println("pela universidade.");
        System.out.println();
        System.out.println("Seus comandos são:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Tenta ir para uma direção. Se há uma saída, entra na
     * nova sala, senão imprime uma mensagem de erro.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // se não há segunda palavra, não sabemos onde ir...
            System.out.println("Ir para onde?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("Não há uma porta!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }
    
    /**
     * "examinar" foi digitado. Imprime uma descrição da sala atual
     * @param command
     */
   
    private void look(Command command)
    {
    	System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * "comer" foi digitado. Imprime uma informação de que o jogador comeu
     */
    private void eat(Command command)
    {
    	System.out.println("Você comeu agora e não está mais com fome.");
    }

    /** 
     * "Sair" foi digitado. Verifica o resto do comando para saber
     * se o usuário quer realmente sair do jogo.
     * @return true, se este comando sair do jogo, falso caso contrário.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Sair de do quê?");
            return false;
        }
        else {
            return true;  // significa que queremos sair
        }
    }
}