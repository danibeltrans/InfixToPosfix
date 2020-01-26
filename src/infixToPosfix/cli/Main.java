package infixToPosfix.cli;

import infixToPosfix.convert.Analysis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) throws IOException {

        String uriFile = Optional.of(args[0]).orElse("input.txt");

        //Reading many lines and return a file with results
        Path input = Paths.get(uriFile);
        List<String> read = Files.readAllLines(input);

        List<String> results = read.stream()
                .mapToDouble(Analysis::convert)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());

        Files.write(Paths.get("output.txt"),
                results,
                StandardOpenOption.CREATE);

    }
}
