package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Test

class UpdateGroceryNameUseCaseTest {

    private lateinit var repository: FakeGroceryRepository
    private lateinit var useCase: UpdateGroceryNameUseCase

    @Before
    fun setup() {
        repository = FakeGroceryRepository()
        useCase = UpdateGroceryNameUseCase(repository)
    }

    @Test
    fun `invoke updates the grocery item name`(): Unit = runTest {
        val item = GroceryItem(id = 1, name = "Milk", isPurchased = false)
        repository.addItem(item)

        useCase(item.id, "Skimmed Milk")

        val updatedItem = repository.observeItems().first().first()
        assertEquals(updatedItem.name, "Skimmed Milk")
        assertEquals(updatedItem.id, item.id)
        assertFalse(updatedItem.isPurchased)
    }
}
