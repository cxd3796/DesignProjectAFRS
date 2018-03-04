package com.flyboiz.afrs.View;


/**
 * Concrete observer in the Observer subsystem.
 */
public class OutputSender implements Output
{
    // State //
    private final InputReader reader;

    /* Note concerning the inclusion of the InputReader in this OutputSender class:
    Given the cyclic nature of this text-based application, it is paramount that the OutputSender know about the
    application's InputReader. The application must operate using this format because the console is only able to
    enter data via polling. This means that no input may occur while the application is performing the query, and
    therefore there must be a way for the output to notify the reader that operations are completed, so that polling
    may be resumed. The application achieves this by providing the outputreader with access to the inputreader and
    the capability to start the polling process via the waitOnInput() method.
     */

    // Constructor //
    public OutputSender(InputReader ir) {
        this.reader = ir;
    }

    /**
     * @param updateText - String of text that the user will see
     */
    @Override
    public void update(String updateText) {
        sendOutput(updateText);
        reader.waitOnInput(); // Once the sender has sent its output, the reader should wait on new input.
    }

    /**
     * Sends the given line of text to the console output.
     * @param updateText - String of text
     */
    private void sendOutput(String updateText){
        System.out.println(updateText); // Print the supplied string.
    }
}
