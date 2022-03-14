package Models.Enum;

/**
 *
 * @author xorigin
 */
public enum Column {
    
    // Common Columns in all tables
    Name,
    NationalID,
    Address,
    Email,
    PhoneNumber,
    Gender,
    DateOfBirth,
    Password,
    DateOfContract,
    
    // Common in Bill, Customer
    GovernmentCode,
    MeterCode,
    
    // Customer Columns
    TypeOfUse,
    Activation,
    PropertyOwnershipContract,
    
    // Administrator Columns
    ID,
    Role,
    
    // Bill Columns
    Num,
    Tariff,
    LastReading,
    CurrentReading,
    Consumption,
    MoneyValue,
    Status,
    ReleaseDate,
    Complain,
    
    // CollectedMoney Column
    TotalCollected;
    
}
