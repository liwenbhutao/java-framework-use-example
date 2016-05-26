package com.ht.test.apache.common.cli;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.cli.*;

/**
 * Created by hutao on 16/5/26.
 * 下午5:14
 */
@RequiredArgsConstructor
@Slf4j
public class CliExample implements Runnable {
    private final String[] args;

    @Override
    public void run() {
        log.info("run cli example");
        final CommandLine commandLine = buildCommandline(args);
        if (commandLine != null) {
            log.info("cli is not null");
            for (final Option option : commandLine.getOptions()) {
                log.info("command line arg [{}] value:{}", option.getLongOpt(), commandLine.getOptionValue(option.getOpt(), ""));
            }
        }

    }

    public static CommandLine buildCommandline(String[] args) {
        final Options options = buildOptions();

        final CommandLineParser parser = new DefaultParser();
        final HelpFormatter hf = new HelpFormatter();
        hf.setWidth(110);
        CommandLine commandLine = null;
        try {
            commandLine = parser.parse(options, args);
            if (commandLine.hasOption('h')) {
                hf.printHelp("producer", options, true);
                return null;
            }
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            hf.printHelp("producer", options, true);
            return null;
        }

        return commandLine;
    }

    private static Options buildOptions() {
        final Options options = new Options();
        // ////////////////////////////////////////////////////
        Option opt = new Option("h", "help", false, "Print help");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("g", "consumerGroup", true, "Consumer Group Name");
        opt.setRequired(true);
        options.addOption(opt);

        opt = new Option("t", "topic", false, "Topic Name");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("s", "subscription", false, "subscription");
        opt.setRequired(false);
        options.addOption(opt);

        opt = new Option("f", "returnFailedHalf", false, "return failed result, for half message");
        opt.setRequired(false);
        options.addOption(opt);
        return options;
    }
}
