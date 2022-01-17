package Transformer;

import health_care_provider.HealthCareInfoProvider;
import health_care_provider.errors.InvalidIdException;
import health_care_provider.models.PersonInsured;

import java.util.HashMap;

public class LabTestsTransformer extends Transformer {

    @Override
    public void Transform() {
        HealthCareInfoProvider healthCareInfoProvider = new HealthCareInfoProvider();
        try {
            for (HashMap<String, String> record : this.getDataManager().getData()) {
                PersonInsured person = healthCareInfoProvider.fetchInfo(Integer.parseInt(record.get("IDNum")), Integer.parseInt(record.get("IDType")));
                record.put("JOIN_DATE", String.valueOf(person.getJoinDate()));
                record.put("HEALTH_CARE_ID", String.valueOf(person.getHealthCareId()));
                record.put("HEALTH_CARE_NAME", String.valueOf(person.getHealthCareName()));
            }
        } catch (InvalidIdException e) {
            e.printStackTrace();
        }

    }
}
