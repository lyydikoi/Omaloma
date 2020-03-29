package com.kasianov.sergei.core_api

import com.kasianov.sergei.core_api.database.DataBaseProvider
import com.kasianov.sergei.core_api.memory.MemoryCacheProvider
import com.kasianov.sergei.core_api.network.NetworkProvider
import com.kasianov.sergei.core_api.repository.RepositoryProvider
import com.kasianov.sergei.core_api.utils.UtilsProvider
import com.kasianov.sergei.core_api.viewmodel.ViewModelsProvider

interface ProvidersFacade : AppProvider,
    DataBaseProvider, NetworkProvider, //ViewModelsProvider,
    MemoryCacheProvider, RepositoryProvider, UtilsProvider