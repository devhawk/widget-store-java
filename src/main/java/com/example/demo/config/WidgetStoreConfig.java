package com.example.demo.config;

import dev.dbos.transact.DBOS;
import dev.dbos.transact.config.DBOSConfig;

import java.util.Objects;

import com.example.demo.service.WidgetStoreService;
import com.example.demo.service.WidgetStoreServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WidgetStoreConfig {

  @Bean
  public WidgetStoreService widgetStoreService(DBOS dbos) {
    var impl = new WidgetStoreServiceImpl(dbos);
    var proxy = dbos.registerWorkflows(WidgetStoreService.class, impl);
    impl.setSelf(proxy);
    return proxy;
  }

  @Bean
  public DBOS dbos(DBOSConfig config) {
    return new DBOS(config);
  }

  @Bean
  public DBOSConfig dbosConfig() {
    String databaseUrl = System.getenv("DBOS_SYSTEM_JDBC_URL");
    if (databaseUrl == null || databaseUrl.isEmpty()) {
      databaseUrl = "jdbc:postgresql://localhost:5432/widget_store_java_sysdb";
    }
    return DBOSConfig.defaults("spring-aspect-demo")
        .withDatabaseUrl(databaseUrl)
        .withDbUser(Objects.requireNonNullElse(System.getenv("PGUSER"), "postgres"))
        .withDbPassword(Objects.requireNonNullElse(System.getenv("PGPASSWORD"), "dbos"))
        .withAdminServer(true)
        .withAppVersion("0.1.0");
  }
}
