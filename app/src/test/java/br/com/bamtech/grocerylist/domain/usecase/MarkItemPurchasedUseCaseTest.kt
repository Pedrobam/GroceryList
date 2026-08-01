package br.com.bamtech.grocerylist.domain.usecase

import br.com.bamtech.grocerylist.data.FakeGroceryRepository
import br.com.bamtech.grocerylist.domain.model.GroceryItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MarkItemPurchasedUseCaseTest {

    private lateinit var repository: FakeGroceryRepository
    private lateinit var useCase: MarkItemPurchasedUseCase

    @Before
    fun setup() {
        repository = FakeGroceryRepository()
        useCase = MarkItemPurchasedUseCase(repository)
    }

    @Test
    fun `Given a non-purchased item, When it is toggled, Then it becomes purchased`() = runTest {
        val item = GroceryItem(id = 1, name = "Eggs", isPurchased = false)
        repository.addItem(item)

        useCase(id = 1)

        val items = repository.observeItems().first()
        Assert.assertTrue("Item should be marked as purchased", items[0].isPurchased)
    }

    @Test
    fun `Given a purchased item, When it is toggled, Then it becomes non-purchased`() = runTest {
        val item = GroceryItem(id = 1, name = "Eggs", isPurchased = true)
        repository.addItem(item)

        useCase(id = 1)

        val items = repository.observeItems().first()
        Assert.assertFalse("Item should be marked as not purchased", items[0].isPurchased)
    }
}
