package Models.Enum;

/**
 *
 * @author xorigin
 */
public enum GovernmentCode {
    
    Alexandria("03"), Aswan("97"), Asyut("88"), Beheira("45"), Beni_Suef("82"), Cairo("02"), Dakahlia("50"), Damietta("57"),
    Faiyum("84"), Gharbia("40"), Giza("02"), Ismailia("64"), Kafr_AlSheikh("47"), Luxor("95"), Matruh("46"), Minya("86"),
    Monufia("48"), New_Valley("92"), North_Sinai("68"), Port_Said("66"), Qalyubia("13"), Qena("96"), Red_Sea("65"),
    Sharqia("55"), Sohag("93"), South_Sinai("69"), Suez("62");

    private final String code;
    
    private GovernmentCode(String code){
    
        this.code = code;
    }
    
    public String getCode(){
    
        return this.code;
    }
    
}