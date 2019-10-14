package FamilyClass;

/**
 * A Class that handles the Custom exceptions. 
 * 
 * @version 1.02 12 Apr 2018
 * @author Joseph Sigar
 */
public class UnknownOpException extends Exception {

    public UnknownOpException() {
        super("UnknownOpException");
    }
    
    public UnknownOpException(char op) {
        super(op + " is an unknown operator.");
    }
    
    public UnknownOpException(String message) {
        super(message);
    }
    
}
