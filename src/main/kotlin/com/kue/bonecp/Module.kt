package com.kue.bonecp

import com.google.inject.AbstractModule

import com.kue.core.NamedDataSourceCollection
import javax.inject.Inject

/**
 * @author Michael Vaughan
 */
class Module @Inject constructor() : AbstractModule() {

    override fun configure() {
        bind(NamedDataSourceCollection::class.java)
                .toProvider(BoneCPDataSourceProvider::class.java)
    }



}
