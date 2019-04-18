package com.raiden.karpukhinomgupsdiplom.permissions

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito

@RunWith(JUnitParamsRunner::class)
class PermissionsViewModelTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var viewModel: PermissionsViewModel
    @Before
    fun setUp() {
        viewModel = PermissionsViewModel()
    }

    fun getNotGrantedPermissions() = arrayOf(
        hashMapOf(
            Pair("123", false)
        ),
        hashMapOf(
            Pair("123", true),
            Pair("123", false),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true)
        ),
        hashMapOf(
            Pair("123", true),
            Pair("123", false),
            Pair("123", true),
            Pair("123", true),
            Pair("123", false)
        ),
        hashMapOf(
            Pair("123", false),
            Pair("123", false),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true)
        ),
        hashMapOf(
            Pair("123", false),
            Pair("123", false),
            Pair("123", true),
            Pair("123", true),
            Pair("123", false)
        ),
        hashMapOf(
            Pair("123", false),
            Pair("123", false),
            Pair("123", false),
            Pair("123", false),
            Pair("123", false)
        )
    )

    @Test
    @Parameters(method = "getNotGrantedPermissions")
    fun `Should post false if permissions is not granted`(parameters: HashMap<String, Boolean>) {
        val observer = Mockito.mock(Observer::class.java) as Observer<Boolean>
        viewModel.isAllPermissionsGranted.observeForever(observer)
        viewModel.checkPermissions(parameters)
        viewModel.isAllPermissionsGranted.observeForever {
            assertFalse(it)
        }
    }

    fun getGrantedPermissions() = arrayOf(
        hashMapOf(
            Pair("123", true)
        ),
        hashMapOf(
            Pair("123", true),
            Pair("123", true)
        ),
        hashMapOf(
            Pair("123", true),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true)
        ),
        hashMapOf(
            Pair("123", true),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true),
            Pair("123", true)
        )
    )

    @Test
    @Parameters(method = "getGrantedPermissions")
    fun `Should post true if all permissions are  granted`(parameters: HashMap<String, Boolean>) {
        val observer = Mockito.mock(Observer::class.java) as Observer<Boolean>
        viewModel.isAllPermissionsGranted.observeForever(observer)
        viewModel.checkPermissions(parameters)
        viewModel.isAllPermissionsGranted.observeForever {
            assertTrue(it)
        }
    }

    @Test
    fun `Should post true permissions are empty`() {
        val parameters = hashMapOf<String, Boolean>()
        val observer = Mockito.mock(Observer::class.java) as Observer<Boolean>
        viewModel.isAllPermissionsGranted.observeForever(observer)
        viewModel.checkPermissions(parameters)
        viewModel.isAllPermissionsGranted.observeForever {
            assertTrue(it)
        }
    }


}