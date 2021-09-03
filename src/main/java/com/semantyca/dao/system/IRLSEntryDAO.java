package com.semantyca.dao.system;


import com.semantyca.model.embedded.RLSEntry;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.UUID;

@UseClasspathSqlLocator
public interface IRLSEntryDAO{

    @SqlQuery
    @RegisterColumnMapper(RLSMapper.class)
    List<RLSEntry> findByDocumentId(@Bind("enityId") UUID enityId);

}



