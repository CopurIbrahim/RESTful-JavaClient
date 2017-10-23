package io.github.bayraktarhasan.AutoKonfigurator;

import io.github.bayraktarhasan.AutoKonfigurator.Business.Controller;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ParserConfigurationException {
        Controller controller = new Controller();
        controller.startProg();

    }
}
