package com.example.appforautotesting.UserViewModelTest

import com.example.appforautotesting.viewModel.UserViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import junit.framework.TestCase.assertEquals

class UserViewModelTest_Fake {

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setUp(){
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown(){
        Dispatchers.resetMain()
    }

    @Test
    fun `load users expected list using fake`() = runTest {
        val viewModel = UserViewModel(FakeUserRepository())

        viewModel.loadUsers()
        dispatcher.scheduler.advanceUntilIdle()
        assertEquals(listOf("Alice", "Bob", "Charlie"), viewModel.users.value)
    }
}