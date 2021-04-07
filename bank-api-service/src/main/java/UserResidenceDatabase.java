/*
 *  MIT License
 *
 *  Copyright (c) 2019 Michael Pogrebinsky - Distributed Systems & Cloud Computing with Java
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Mock database that contains a map from a user to its country of residence
 */
public class UserResidenceDatabase {
    private static final String USER_RESIDENCE_FILE = "user-residence.txt";
    private final Map<String, String> userToResidenceMap;

    public UserResidenceDatabase(){
        this.userToResidenceMap = loadUsersResidenceFromFile();
    }

    /**
     * Returns the user's country of residence
     */
    public String getUserResidence(String user) {
        if (!userToResidenceMap.containsKey(user)) {
            throw new RuntimeException("user " + user + " doesn't exist");
        }

        return userToResidenceMap.get(user);
    }

    private Map<String, String> loadUsersResidenceFromFile() {
        Map<String, String> userToResidence = new HashMap<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(USER_RESIDENCE_FILE);

        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String []userResidencePair = line.split(" ");
            userToResidence.put(userResidencePair[0], userResidencePair[1]);
        }
        return Collections.unmodifiableMap(userToResidence);
    }


}
