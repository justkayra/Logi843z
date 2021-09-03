package com.semantyca.service;

import com.semantyca.dto.IPage;
import com.semantyca.dto.Page;

import javax.inject.Singleton;

@Singleton
public class ApplicationDataService {
    public IPage populate() {
        return new Page();
    }
}
