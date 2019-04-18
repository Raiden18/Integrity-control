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
            Pair("312", false),
            Pair("asd", true),
            Pair("zxc", true),
            Pair("4563", true)
        ),
        hashMapOf(
            Pair("zxc", true),
            Pair("123", false),
            Pair("qwe", true),
            Pair("zxcdsf", true),
            Pair("57yrtgdgfsdfg", false)
        ),
        hashMapOf(
            Pair("asdzxc", false),
            Pair("adafgdghfgbfv", false),
            Pair("qwe143rwef", true),
            Pair("123wesa", true),
            Pair("zvcxbnc", true)
        ),
        hashMapOf(
            Pair("324terf", false),
            Pair("asdzcx", false),
            Pair("e32ewds", true),
            Pair("3wedsc", true),
            Pair("12eqwsa", false)
        ),
        hashMapOf(
            Pair("weds", false),
            Pair("qwsaxz", false),
            Pair("1321wq", false),
            Pair("ewdfscx", false),
            Pair("32ewfeg", false)
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
            Pair("asd", true)
        ),
        hashMapOf(
            Pair("zxcxzc", true),
            Pair("12edsfv", true)
        ),
        hashMapOf(
            Pair("thregdbvx", true),
            Pair("asdzcxvb", true),
            Pair("sdafg", true),
            Pair("qwadsxzcvb", true)
        ),
        hashMapOf(
            Pair(";lkmnb", true),
            Pair("sdafvxc", true),
            Pair("lkjhbnm", true),
            Pair("xcvbnm", true),
            Pair(".,mnb v", true),
            Pair("oiuyhghjkmn", true),
            Pair("bhgyuikjn", true)
        )
    )

    @Test
    @Parameters(method = "getGrantedPermissions")
    fun `Should post true if all permissions are  granted`(parameters: HashMap<String, Boolean>) {
        val observer = Mockito.mock(Observer::class.java) as Observer<Boolean>
        viewModel.isAllPermissionsGranted.observeForever(observer)
        val asaa = viewModel.checkPermissions(parameters)
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