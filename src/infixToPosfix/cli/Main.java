package infixToPosfix.cli;

import infixToPosfix.convert.Analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println(Analysis.convert("( ( 1 + 2 ) - 3 * ( 4 / 5 ) ) + 6"));

        /*if (args.length == 0){

            System.out.println("Please provide a path or filename as input argument :)");
        }

        //Reading many lines and return a file with results
        Path path = Paths.get(args[0]);
        List<String> read = Files.readAllLines(path);*/

    }
}
