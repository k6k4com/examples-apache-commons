package com.k6k4.examples.apache.commons.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * Created by k6k4 on 16/10/29.
 */
public class CliExample {
    public static void main(String[] args) throws ParseException {

        Options options = new Options();
        options.addOption("a", false, "hello a");
        options.addOption("b", true, "hello b");
        options.addOption("c", "c-long", false, "hello c");
        options.addOption("d", "d-long", true, "hello d");

        Option e = OptionBuilder.withArgName("eArg")
                .hasArg()
                .withDescription("hello e")
                .create("e");

        Option f = OptionBuilder.withArgName("key=value")
                .hasArgs(2)
                .withValueSeparator()
                .withDescription("hello f")
                .create("f");

        options.addOption(e);
        options.addOption(f);

        args = new String[]{"-a", "-b", "bValue", "--c-long", "-d", "dValue", "-fKey=fValue"};

        handle(options, args);

    }

    public static void handle(Options options, String[] args) throws ParseException {
        CommandLineParser parser = new DefaultParser();
        CommandLine cl = parser.parse(options, args);

        HelpFormatter hf = new HelpFormatter();
        hf.printHelp("Help Message", options);

        if (cl.hasOption("a")) {
            System.out.println("-a");
        }

        if (cl.hasOption("b")) {
            System.out.println("-b:" + cl.getOptionValue("b"));
        }

        if (cl.hasOption("c-long")) {
            System.out.println("--c-long");
        }

        System.out.println("--d-long:" + cl.getOptionValue("d-long", "dValueDefault"));

        System.out.println("-e:" + cl.getOptionValue("e", "eValueDefault"));

        if (cl.hasOption("f")) {
            System.out.println("-f:" + cl.getOptionValue("f"));
        }

    }
}
