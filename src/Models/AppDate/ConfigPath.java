package Models.AppDate;

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
    
        return this.configFilePath;
    }
    
}
