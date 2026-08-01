package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class UpdateGroceryNameUseCaseTest {

    private lateinit var fakeGroceryRepository: FakeGroceryRepository
    private lateinit var updateGroceryNameUseCase: UpdateGroceryNameUseCase

    @Before
    fun setup() {
        fakeGroceryRepository = FakeGroceryRepository()
        updateGroceryNameUseCase = UpdateGroceryNameUseCase(fakeGroceryRepository)
    }

    @Test
    fun `invoke updates the grocery item name`(): Unit = runTest {
        val item = GroceryItem(id = 1, name = "Milk", isPurchased = false)
        fakeGroceryRepository.addItem(item)

        updateGroceryNameUseCase(item.id, "Skimmed Milk")

        val updatedItem = fakeGroceryRepository.observeItems().first().first()
        assert(updatedItem.name == "Skimmed Milk")
        assert(updatedItem.id == item.id)
    }
}
