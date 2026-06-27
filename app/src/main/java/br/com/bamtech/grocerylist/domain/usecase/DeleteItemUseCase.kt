package br.com.bamtech.grocerylist.domain.usecase

interface DeleteItemUseCase {

    suspend fun invoke(id: Long)
}