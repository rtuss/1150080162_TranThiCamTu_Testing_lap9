package framework.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import framework.model.UserData;

public class JsonReader {

    public static List<UserData> readUsers(String filePath) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(new File(filePath), new TypeReference<List<UserData>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Lỗi đọc JSON: " + filePath, e);
        }
    }

    public static Object[][] getUsersAsDataProvider(String filePath) {
        List<UserData> users = readUsers(filePath);
        List<Object[]> data = new ArrayList<>();

        for (UserData user : users) {
            data.add(new Object[]{user});
        }

        return data.toArray(new Object[0][]);
    }
}