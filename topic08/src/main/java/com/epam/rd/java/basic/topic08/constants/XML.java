package com.epam.rd.java.basic.topic08.constants;

public enum XML {
    TEST("Test"), QUESTION("Question"), QUESTION_TEXT("QuestionText"), ANSWER("Answer"),

    // attribute name
    CORRECT("correct");

    private String value;

    XML(String value) {
        this.value = value;
    }

    /**
     * Determines if a name is equal to the string value wrapped by this enum element.<br/>
     * If a SAX/StAX parser make all names of elements and attributes interned you can use
     * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
     * @param name string to compare with value.
     * @return value.equals(name)
     */
    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}
