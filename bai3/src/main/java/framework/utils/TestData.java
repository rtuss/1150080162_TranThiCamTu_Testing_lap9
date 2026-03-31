package framework.utils;

public class TestData {
    private String username;
    private String password;
    private String expectedUrl;
    private String expectedError;
    private String description;
    private String sourceSheet;

    public TestData(String username, String password, String expectedUrl,
                    String expectedError, String description, String sourceSheet) {
        this.username = username;
        this.password = password;
        this.expectedUrl = expectedUrl;
        this.expectedError = expectedError;
        this.description = description;
        this.sourceSheet = sourceSheet;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedUrl() {
        return expectedUrl;
    }

    public String getExpectedError() {
        return expectedError;
    }

    public String getDescription() {
        return description;
    }

    public String getSourceSheet() {
        return sourceSheet;
    }

    @Override
    public String toString() {
        return description;
    }
}