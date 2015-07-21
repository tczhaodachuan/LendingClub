package org.p2p.lending.club.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by tczhaodachuan on 7/20/2015.
 */
public class Main {
    private static final Logger LOG = LogManager.getLogger();
    private static String serviceName;
    private static String version;
    private static String context;

    public static void main(String[] args) {
        init(args);
        LOG.info("Loading context file {}", context);
        try {
            AbstractXmlApplicationContext springContext = new ClassPathXmlApplicationContext(context);
            springContext.start();
            springContext.registerShutdownHook();
        } catch (Exception e) {
            LOG.fatal("Failed to start the program");
            System.exit(-1);
        }
    }

    private static void init(String[] args) {
        if (args.length < 3) {
            LOG.fatal("Wrong format arguments passed in.");
            System.exit(-1);
        } else {
            context = args[0];
            serviceName = args[1];
            version = args[2];
        }
    }

    public static String getServiceName() {
        return serviceName;
    }

    public static String getVersion() {
        return version;
    }
}
