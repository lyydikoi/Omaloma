package com.kasianov.sergei.core_api

import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import com.kasianov.sergei.core_api.utils.UtilsProvider

interface ProvidersFacade :
    AppProvider,
    DataBaseProvider,
    NetworkProvider,
    MemoryCacheProvider,
    RepositoryProvider,
    UtilsProvider
