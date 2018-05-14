package org.carmanager.veiculo.configuration;

import org.apache.log4j.Logger;
import org.carmanager.veiculo.controller.VeiculoController;
import org.carmanager.veiculo.repository.VeiculoRepository;
import org.carmanager.veiculo.service.VeiculoService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.ws.rs.core.Configurable;
import java.sql.SQLException;

public class RestApp {

    private Server server = null;
    private DBServer dbServer = null;
    private static final int PORT = 8080;
    private static final Logger log = Logger.getLogger(RestApp.class);

    public static void main(String[] args) {
        RestApp restApp = null;
        try {
            restApp = new RestApp();
            restApp.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() throws Exception {
        if (server == null) {
            server = createServer();
            log.info("jetty server created");
        }
        server.start();
        //add shutdown hook for jetty server for graceful shutdown
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    log.info("shutdown hook invoked");
                    server.stop();
                    log.info("jetty server stopped");
                    dbServer.shutDownDBServer();
                } catch (Exception e) {
                    log.info("error stoping jetty server", e);
                }
            }
        });
        server.join();
    }

    private Server createServer() throws Exception {
        // Create resource config
        ResourceConfig resourceConfig = new ResourceConfig();
        // Configure all the external config
        setUpResources(resourceConfig);

        // Create ServletContainer and ServletHolder
        ServletContainer servletContainer = new ServletContainer(resourceConfig);
        ServletHolder servletHolder = new ServletHolder(servletContainer);

        // specify the servlet which classes to load
        servletHolder.setInitParameter("jersey.config.server.provider.packages", "com.lijubjohn");

        // Create ServletContextHandler
        ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        servletContextHandler.setContextPath("/");

        // Add all servlets to the servlet context
        servletContextHandler.addServlet(servletHolder, "/*");

        // create embedded jetty server
        server = new Server(PORT);

        // Add the ServletContextHandler to the server
        server.setHandler(servletContextHandler);
        return server;
    }

    private void setUpResources(Configurable<?> configurable) throws Exception {
        // initialize DB server
        if (dbServer == null) {
            dbServer = new DBServer();
            dbServer.initializeDB();
        }
        // Register rest Controllers
        configurable.register(new VeiculoController(new VeiculoService(new VeiculoRepository(dbServer.getConnection()))));
        // Register the customer exception mappers
        configurable.register(VeiculoNotFoundEx.class);
        configurable.register(RestException.class);
    }
}
