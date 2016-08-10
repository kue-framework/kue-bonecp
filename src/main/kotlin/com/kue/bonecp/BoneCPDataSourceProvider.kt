package com.kue.bonecp

import com.jolbox.bonecp.BoneCPDataSource
import com.kue.core.NamedDataSource
import com.kue.core.NamedDataSourceCollection
import com.typesafe.config.Config
import com.typesafe.config.ConfigObject
import javax.inject.Inject
import javax.inject.Provider
import javax.sql.DataSource


/**
 * @author Michael Vaughan
 */
class BoneCPDataSourceProvider @Inject constructor(val config: Config) : Provider<NamedDataSourceCollection> {

    override fun get(): NamedDataSourceCollection {
        val dco = config.getObject("db")
        val dc = dco?.toConfig()
        val dataSources =  dco?.keys?.map { key ->
            val ds = dataSource(key, dco, dc)
            NamedDataSource(key, ds)
        }
        return NamedDataSourceCollection(dataSources ?: emptyList())
    }

    private fun dataSource(key: String, dco: ConfigObject, dc: Config?) : DataSource{
        val dsc = dc?.getConfig(key)
        val driver = dsc?.getString("driver")
        val url = dsc?.getString("url")
        val username = dsc?.getString("username")
        val password = dsc?.getString("password")
        Class.forName(driver)
        return BoneCPDataSource().apply {
            this.jdbcUrl = url
            this.username = username
            this.password = password
        }
    }
}