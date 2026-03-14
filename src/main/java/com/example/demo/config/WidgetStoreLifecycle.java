package com.example.demo.config;

import dev.dbos.transact.DBOS;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicBoolean;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class WidgetStoreLifecycle implements SmartLifecycle {
  private static final Logger logger = LoggerFactory.getLogger(WidgetStoreLifecycle.class);

  private final DBOS dbos;
  private final ProductRepository productRepository;
  private final AtomicBoolean running = new AtomicBoolean(false);

  @Value("${spring.datasource.url}")
  private String jdbcUrl;

  @Value("${spring.datasource.username}")
  private String username;

  @Value("${spring.datasource.password}")
  private String password;

  @Value("${spring.application.name}")
  private String appName;

  public WidgetStoreLifecycle(DBOS dbos, ProductRepository productRepository) {
    this.dbos = dbos;
    this.productRepository = productRepository;
  }

  @Override
  public void start() {
    seedProducts();
    if (!running.getAndSet(true)) {
      dbos.launch();
    } else {
      logger.debug("start called when already launched");
    }
  }

  // seed the demo app database with the widget product if it's not already there
  private void seedProducts() {
    if (!productRepository.existsById(1)) {
      Product widget = new Product();
      widget.setProductId(1);
      widget.setProduct("Premium Quality Widget");
      widget.setDescription("Enhance your productivity with our top-rated widgets!");
      widget.setInventory(100);
      widget.setPrice(new BigDecimal("99.99"));
      productRepository.save(widget);
      logger.info("Seeded initial product data");
    }
  }

  @Override
  public void stop() {
    if (running.getAndSet(false)) {
      dbos.shutdown();
    } else {
      logger.debug("stop called when already shutdown");
    }
  }

  @Override
  public boolean isRunning() {
    return running.get();
  }

  @EventListener
  public void handleWebServerInit(WebServerInitializedEvent event) {
    int port = event.getWebServer().getPort();
    logger.info("🚀 {} Server is running on http://localhost:{}", appName, port);
  }
}
