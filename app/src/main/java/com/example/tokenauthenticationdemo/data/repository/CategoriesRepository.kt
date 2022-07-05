package com.example.tokenauthenticationdemo.data.repository

import androidx.lifecycle.LiveData
import com.example.tokenauthenticationdemo.data.local.CustomItemEntity
import com.example.tokenauthenticationdemo.data.local.ItemDataBase
import com.example.tokenauthenticationdemo.data.local.ItemsEntitiy
import com.example.tokenauthenticationdemo.data.remote.ApiService
import com.example.tokenauthenticationdemo.data.remote.SafeApiCall
import com.example.tokenauthenticationdemo.utils.Resource
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import timber.log.Timber

class CategoriesRepository(
    private val apiService: ApiService,
    private val itemDataBase: ItemDataBase
):SafeApiCall(){
    suspend fun getItem(): Flow<Resource<CustomItemEntity>>{
        return flow {
            emit(Resource.Loading())

            val item = itemDataBase.dao.getItems()
            Timber.d("item: $item")

            if (item != null){
                emit(Resource.Success(data = item))
            }else{
                try {
                    val data = apiService.categories()
                    //itemDataBase.dao.deleteItem()
                    Timber.d("data: $data")

                   val customItem = CustomItemEntity(
                       diagram = data.data.diagrams,
                       categories = data.data.categories
                   )

                    itemDataBase.dao.insertItem(customItem)

                    val databaseItems = itemDataBase.dao.getItems()
                    if (databaseItems != null) {
                        emit(Resource.Success(data = databaseItems))
                    }
                }catch (e: HttpException){
                    emit(
                        Resource.Failure(
                            message = "Oops something went wrong!",
                            data = item
                        )
                    )
                }
            }
        }
    }


    suspend fun deleteItem(){
        itemDataBase.dao.deleteItem()
    }
    suspend fun insertItem(item:CustomItemEntity){
        itemDataBase.dao.insertItem(item)
    }
}