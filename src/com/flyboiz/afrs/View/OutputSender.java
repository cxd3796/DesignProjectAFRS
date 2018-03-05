package com.flyboiz.afrs.View;


/**
 * Concrete observer in the Observer subsystem.
 */
public class OutputSender implements Output
{
    // State //

    // Constructor //
    public OutputSender() {

    }

    /**
     * @param updateText - String of text that the user will see
     */
    @Override
    public void update(String updateText) {
        sendOutput(updateText);
        //reader.waitOnInput(); // Once the sender has sent its output, the reader should wait on new input.
    }

    /**
     * Sends the given line of text to the console output.
     * @param updateText - String of text
     */
    private void sendOutput(String updateText){
        System.out.println(updateText); // Print the supplied string.
    }
}
