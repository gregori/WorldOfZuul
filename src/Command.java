/**
 * Esta classe é parte da aplicação "Mundo de Zuul". 
 */

public class Command
{
    private String commandWord;
    private String secondWord;

    /**
     * Cria um objeto de comando. A primeira e segunda palavras devem ser fornecidas,
     * mas uma ou ambas podem ser null.
     * @param firstWord A primeira palavra do comando. Null se o comando não for
     *                  reconhecido.
     * @param secondWord A segunda palavra do comando.
     */
    public Command(String firstWord, String secondWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
    }

    /**
     * Retorna a palavra do comando (a primeira palavra) deste comando. Se o 
     * comando não foi compreendido, o resultado é null.
     * @return A palavra do comando.
     */
    public String getCommandWord()
    {
        return commandWord;
    }

    /**
     * @return A segunda palavra deste comando. Retorna null se não há uma 
     * segunda palavra.
     */
    public String getSecondWord()
    {
        return secondWord;
    }

    /**
     * @return true se esse comando não foi compreendido.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * @return true se o comando tem uma segunda palavra.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
}
