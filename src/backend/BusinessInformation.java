package backend;

import database.BusinessTableManager;

public class BusinessInformation extends AccountInformation{
    private Address address;

    public BusinessInformation(int businessId) {
        super(businessId, new BusinessTableManager());
        this.address = new BusinessTableManager().getAddressFromDatabase(businessId);
    }

}