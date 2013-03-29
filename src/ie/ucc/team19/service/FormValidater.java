package ie.ucc.team19.service;

import java.util.HashMap;
import java.util.Map;

public class FormValidater {
    private HashMap<String, String> formTests = new HashMap<String, String>();

    public FormValidater() {
        formTests.put("firstName", ".{1,30}");
        formTests.put("lastName", ".{1,30}");
        formTests.put("email", ".{5,50}");
        formTests.put("passwordHash", ".{6,32}");
        formTests.put("addressLine1", ".{1,30}");
        formTests.put("addressLine2", ".{1,30}");
        formTests.put("addressLine3", ".{1,30}");
        formTests.put("country", ".{1,30}");
        formTests.put("telephone", ".{0,30}");
        formTests.put("gender", "('M'|'F'){1}");
        formTests.put("courseTitle", ".{1,30}");
        formTests.put("fee", ".{1,30}");
        formTests.put("courseCategory", ".{1,30}");
    }

    public String checkForm(Map<String, String[]> formValues) {
        for(String input : formValues.keySet()) {
            String test = formTests.get(input);
            boolean result = true;
            if(test != null) {
                result = formValues.get(input)[0].matches(test);
            }
            if(!result) {
                return "Problem with input: " + input;
            }
        }
        return null;
    }
}
