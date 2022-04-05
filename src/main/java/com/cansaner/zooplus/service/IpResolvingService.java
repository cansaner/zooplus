package com.baeldung.crud.service;

import com.baeldung.crud.service.model.IPResult;

/**
 * Created by cansaner on 04/04/22.
 */
public interface IPResolvingService {
  IPResult resolve(String ip);
}
