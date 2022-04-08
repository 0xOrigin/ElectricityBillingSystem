package Models.AppDate;

import java.io.File;

/**
 *
 * @author xorigin
 */
class ConfigPath {
    
    private final String configFilePath;

    ConfigPath() {
        
        this.configFilePath = "lib/App_Data/AppConfig/App.config";
    }
    
    ConfigPath(String path){
    
        this.configFilePath = path;
    }
    
    String get(){
    
        if(!this.isValidPath())
            this.endOfExecution();
        
        return this.configFilePath;
    }
    
    private boolean isValidPath(){
    
        return new File(this.configFilePath).exists();
    }
    
    private void endOfExecution(){
    
        System.out.println("The App config file not found at: " + this.configFilePath);
        System.exit(0);
    }
    
}
