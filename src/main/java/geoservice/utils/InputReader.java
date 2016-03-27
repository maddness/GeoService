package geoservice.utils;

import geoservice.model.Cell;
import geoservice.model.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static geoservice.model.Cell.newCell;
import static geoservice.model.User.newUser;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class InputReader {
    /**
     * Process cells file and creates list of cells.
     *
     * @param filePath path to the file with grid data
     * @return list of cells
     * @throws NumberFormatException if there are any problems with data parsing
     * @throws RuntimeException if any other IO errors occurred
     */
    public static List<Cell> readCells(String filePath) {
        File file = createFileObject(filePath);
        List<Cell> cells = new ArrayList<>(1000);

        String line = "";
        try (BufferedReader is = new BufferedReader(new FileReader(file))) {
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                cells.add(newCell(
                        parseInt(values[0]),
                        parseInt(values[1]),
                        parseDouble(values[2])
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with processing grid file");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Problem with parsing grid file at line: '" + line + "'. " + e.getMessage());
        }

        return cells;
    }

    /**
     * Process users file and creates list of users.
     *
     * @param filePath path to the file with users data
     * @return list of users
     * @throws NumberFormatException if there are any problems with data parsing
     * @throws RuntimeException if any other IO errors occurred
     */
    public static List<User> readUsers(String filePath) {
        File file = createFileObject(filePath);
        List<User> users = new ArrayList<>(100000);

        String line = "";
        try (BufferedReader is = new BufferedReader(new FileReader(file))) {
            while ((line = is.readLine()) != null) {
                String[] values = line.split(",");
                users.add(newUser(
                        parseInt(values[0]),
                        parseDouble(values[1]),
                        parseDouble(values[2])
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException("Problem with processing users file");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Problem with parsing users file at line: '" + line + "'. " + e.getMessage());
        }

        return users;
    }

    private static File createFileObject(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new RuntimeException("File with path '" + filePath + "' not found.");
        }
        return file;
    }
}
