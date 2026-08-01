package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RestoreItemUseCaseTest {

    private lateinit var repository: FakeGroceryRepository
    private lateinit var useCase: RestoreItemUseCase

    @Before
    fun setup() {
        repository = FakeGroceryRepository()
        useCase = RestoreItemUseCase(repository)
    }

    @Test
    fun `When it is restored, Then the original ID, name, and purchased state return`() = runTest {
        val item = GroceryItem(id = 42, name = "Milk", isPurchased = true)

        useCase(item)

        val items = repository.observeItems().first()
        assertEquals(1, items.size)
        assertEquals(item, items[0])
    }
}
