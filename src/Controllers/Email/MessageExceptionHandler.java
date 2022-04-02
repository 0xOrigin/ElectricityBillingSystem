package Controllers.Email;

/**
 *
 * @author xorigin
 */
class MessageExceptionHandler {
    
    static void handle(String exception, String messageSubject, String messageText){
    
        print(exception);
        System.out.println("-----------------------------------");
        System.out.println("Mail subject : " + messageSubject);
        System.out.println("Mail text : \n" + messageText);
        System.out.println("----------------------------------------------------------");
    }
    
    static void print(String exception){
    
        System.out.println(exception);
    }
}
