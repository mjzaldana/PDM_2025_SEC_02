package com.example.appforautotesting.UserDetailViewModelTest

import com.example.appforautotesting.repository.UserDetail
import com.example.appforautotesting.repository.UserDetailRepository
//import com.example.appforautotesting.viewModel.UserDetailViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue
import kotlin.test.assertFalse

