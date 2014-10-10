
package Util;

public class ReturnValidate {
    private String message;
    private boolean valid;
    
    public ReturnValidate(String message){
        if (message == null || message.equals("")){
            this.valid = true;
            this.message = "";
        } else {
            this.valid = false;
            this.message = message;
        }
    }

    public String getMessage() {
        return message;
    }

    public boolean isValid() {
        return valid;
    }   
    
}
