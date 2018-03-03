package Controller;

/**
 * Encapsulates the query from the client.
 * Command within the Command pattern
 * Product within the Factory pattern
 * Todo- create concrete Queries
 */
public interface Query
{
    /**
     * Todo- Figure out what the command should produce
     * @return A string that  represents the desired result.
     */
    public String generateResponse();
}
