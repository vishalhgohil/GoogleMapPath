package com.app.data.datasource

import com.app.data.repository.UserRepository
import com.app.data.service.AuthenticationService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserLiveDataSource @Inject constructor(private val authenticationService: AuthenticationService) : BaseDataSource(), UserRepository {


}
