import java.util.Scanner;

/**
 * Esta classe é parte da aplicação "Mundo de Zuul".
 * 
 */

public class Parser {
	private CommandWords commands; // contém os comandos válidos
	private Scanner reader; // origem da entrada de comandos

	/**
	 * Cria um parser para ler da janela de terminal.
	 */
	public Parser() {
		commands = new CommandWords();
		reader = new Scanner(System.in);
	}

	/**
	 * @return O próximo comando do usuário.
	 */
	public Command getCommand() {
		String inputLine; // vai conter a entrada completa
		String word1 = null;
		String word2 = null;

		System.out.print("> "); // imprime o prompt

		inputLine = reader.nextLine();

		// Encontra até duas palavras na linha.
		Scanner tokenizer = new Scanner(inputLine);
		if (tokenizer.hasNext()) {
			word1 = tokenizer.next(); // pega a primeira palavra
			if (tokenizer.hasNext()) {
				word2 = tokenizer.next(); // pega a segunda palavra
				// nota: simplesmente ignoramos o resto da linha.
			}
		}

		// Agora checa se esta palavra é conhecida. Caso positivo, cria um
		// comando com ela. Se não, cria um comando "null"
		// (para comando desconhecido).
		if (commands.isCommand(word1)) {
			return new Command(word1, word2);
		} else {
			return new Command(null, word2);
		}
	}

	/**
	 * Mostra todos os comandos válidos.
	 */
	public String showCommands() {
		return commands.getCommandList();
	}
}
