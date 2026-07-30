package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class AddItemUseCaseTest {

    private lateinit var repository: FakeGroceryRepository
    private lateinit var useCase: AddItemUseCase

    @Before
    fun setup() {
        repository = FakeGroceryRepository()
        useCase = AddItemUseCase(repository)
    }

    @Test
    fun `invoke adds an unpurchased item`() = runTest {
        useCase("Milk")

        val items = repository.observeItems().first()

        assertEquals(1, items.size)
        assertEquals("Milk", items.first().name)
        assertFalse( items.first().isPurchased)
    }

}
