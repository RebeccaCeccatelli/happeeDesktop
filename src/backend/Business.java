package backend;

import database.ReviewDAO;

import java.sql.Time;

public class Business extends Account {
    private BusinessDetails details;

    public Business(int id) {
        this.information = new BusinessInformation(id);
        this.details = new BusinessDetails(id);
        this.reviews = new ReviewDAO().getReviewsByAccountId(id, "business_id");
    }

    public void saveBusinessType(String businessType) {
        details.saveBusinessType(businessType);
    }

    public void saveAccessPrice(float accessPrice) {
        details.saveAccessPrice(accessPrice);
    }

    public void saveAccessCode(int accessCode) { details.saveAccessCode(accessCode); }

    public void saveTime(Time time, String type) {
        details.saveTime(time, type);
    }

    public void saveSpecificField(Object... params) {
        information.saveSpecificField(params);
    }

    public BusinessDetails getDetails() {
        return details;
    }

    public int getAccessCode() {
        return details.getAccessCode();
    }

    public Object getSpecificField() {
        return information.getSpecificField();
    }

    public float getAverageRating() {
        float average = 0;
        if (reviews.size() != 0) {
            for (Review review : reviews) {
                average += review.getRating();
            }
            return average / reviews.size();
        }
        return average;
    }
}
