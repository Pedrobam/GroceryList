package br.com.bamtech.grocerylist.domain.usecase

interface AddItemUseCase {

    suspend fun invoke(name: String)
}