package com.example.appforautotesting.UserViewModelTest.UserViewModelTest_Mock

import com.example.appforautotesting.repository.UserRepository
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
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.times
import org.mockito.kotlin.whenever

class UserViewModelTest_Mock {
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
    fun `load users expected list using mock`() = runTest {
        val mockRepo = mock(UserRepository::class.java)
        `when`(mockRepo.getUsers()).thenReturn(listOf("Alice", "Bob", "Charlie"))

        val viewModel = UserViewModel(mockRepo)
        viewModel.loadUsers()
        dispatcher.scheduler.advanceUntilIdle()

        assertEquals(listOf("Alice", "Bob", "Charlie"), viewModel.users.value)

        verify(mockRepo.getUsers())
    }

    @Test
    fun `load users sets empty list when repository return no users`() = runTest   {
        val mockRepo = mock<UserRepository>()
        `when`(mockRepo.getUsers()).thenReturn(emptyList())

        val viewModel = UserViewModel(mockRepo)
        viewModel.loadUsers()
        advanceUntilIdle()

        assertEquals(emptyList<String>(), viewModel.users.value)
    }

    @Test
    fun `load users handles exception thrown by repository`() = runTest {
        val mockRepo = mock<UserRepository>()
        `when`(mockRepo.getUsers()).thenThrow(RuntimeException("Error de red"))

        val viewModel = UserViewModel(mockRepo)
        viewModel.loadUsers()
        advanceUntilIdle()

        assertEquals(emptyList<String>(), viewModel.users.value)
    }

    @Test
    fun `load users handles multiple calls safely`() = runTest {
        val mockRepo = mock<UserRepository>()
        `when`(mockRepo.getUsers()).thenReturn(listOf("Alice", "Bob", "Charlie"))

        val viewModel = UserViewModel(mockRepo)

        repeat(10000) { viewModel.loadUsers() }
        advanceUntilIdle()

        //Vericar si la llamada se ejecuto
        verify(mockRepo, atLeastOnce()).getUsers()
    }
}
