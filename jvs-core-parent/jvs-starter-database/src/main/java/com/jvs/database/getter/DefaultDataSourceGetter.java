package com.jvs.database.getter;

import com.jvs.database.entity.DatabaseInfo;

/**
 * @Author: GuoZi
 */
public class DefaultDataSourceGetter implements IDataSourceGetter {

    private DatabaseInfo databaseInfo;

    @Override
    public DatabaseInfo getCurrent() {
        return this.databaseInfo;
    }

    public void init(DatabaseInfo databaseInfo) {
        this.databaseInfo = databaseInfo;
    }

}
