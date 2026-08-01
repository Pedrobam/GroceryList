package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class DeleteItemUseCaseTest {

    private lateinit var repository: FakeGroceryRepository
    private lateinit var useCase: DeleteItemUseCase

    @Before
    fun setup() {
        repository = FakeGroceryRepository()
        useCase = DeleteItemUseCase(repository)
    }

    @Test
    fun `invoke deletes an item`() = runTest {
        val item = GroceryItem(id = 1, name = "Milk", isPurchased = false)
        repository.addItem(item)

        useCase(item.id)

        val items = repository.observeItems().first()
        assertTrue(items.isEmpty())
    }
}
