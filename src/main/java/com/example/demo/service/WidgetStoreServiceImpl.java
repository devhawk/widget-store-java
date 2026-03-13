package com.example.demo.service;

import dev.dbos.transact.DBOS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WidgetStoreServiceImpl implements WidgetStoreService {
  private static final Logger logger = LoggerFactory.getLogger(WidgetStoreServiceImpl.class);

  private final DBOS dbos;
  private WidgetStoreService self;

  public WidgetStoreServiceImpl(DBOS dbos) {
    this.dbos = dbos;
  }

  public void setSelf(WidgetStoreService self) {
    this.self = self;
  }
}
