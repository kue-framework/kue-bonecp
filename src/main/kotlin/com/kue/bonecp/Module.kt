package com.kue.bonecp

import com.google.inject.AbstractModule
import javax.sql.DataSource

/**
 * @author Michael Vaughan
 */
class Module : AbstractModule() {

    override fun configure() {
        bind(DataSource::class.java).toProvider(BoneCPDataSourceProvider::class.java).asEagerSingleton()
    }
}