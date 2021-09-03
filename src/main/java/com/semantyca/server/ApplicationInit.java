package com.semantyca.server;

import com.semantyca.service.ApplicationDataService;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;

@ApplicationScoped
public class ApplicationInit {

    @Inject
    private ApplicationDataService applicationDataService;

    private static final Logger LOGGER = LoggerFactory.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) throws SQLException, IOException {
        LOGGER.info("The application is starting...{}", EnvConst.APP_ID);

    }

    void onStop(@Observes ShutdownEvent ev) {
        LOGGER.info("The application is stopping...");
    }

}
