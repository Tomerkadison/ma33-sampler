package Transformer.LabResultsTrasnsformer;

import Loader.Loader;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import Logger.MyLogger;
import Transformer.Transformer;

public class LabResultsValidation extends Transformer {
    private Logger logger = new MyLogger().getLogger();

    @Override
    public void Transform() {
        for (HashMap<String, String> record : this.dataManager.getData()) {
            checkIDNum(record);
            checkLabCode(record);
        }
    }

    private void checkIDNum(HashMap<String, String> record) {
        if (record.get("IDType") == "0") {
            if (record.get("IDNum").length() != 9) {
                this.logger.log(Level.SEVERE, "IDNum:" + record.get("IDNum")
                        + " has " + record.get("IDNum").length() + " digits instead of 9.");
            }
            for (int i = 0; i < record.get("IDNum").length(); i++) {
                if (!Character.isDigit(record.get("IDNum").charAt(i))) {
                    this.logger.log(Level.SEVERE, "IDNum: " + record.get("IDNum") + "contains char "
                            + record.get("IDNum").charAt(i) + " which is not a number.");
                }
            }
        }
    }

    private void checkLabCode(HashMap<String, String> record) {
        if (record.get("LabCode").length() == 5) {
            if (!Character.isDigit(record.get("LabCode").charAt(0)) || !Character.isDigit(record.get("LabCode").charAt(1))
                    || !Character.isLetter(record.get("LabCode").charAt(2)) || !Character.isLetter(record.get("LabCode").charAt(3))
                    || !Character.isDigit(record.get("LabCode").charAt(4))) {
                this.logger.log(Level.SEVERE, "LabCode's structure dose not match ddccd");
            } else {
                this.logger.log(Level.SEVERE, "LabCode's structure isnt in the right length");
            }
        }
    }

    public void checkAntidotes() {

    }
}
