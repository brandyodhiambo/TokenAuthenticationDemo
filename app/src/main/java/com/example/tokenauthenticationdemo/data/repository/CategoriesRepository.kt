package com.example.tokenauthenticationdemo.data.repository

import com.example.tokenauthenticationdemo.data.local.categories.CustomItemEntity
import com.example.tokenauthenticationdemo.data.local.database.ItemDataBase
import com.example.tokenauthenticationdemo.data.local.parts.PartsEntity
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
){
    suspend fun getItem(): Flow<Resource<CustomItemEntity>>{
        return flow {
            emit(Resource.Loading())

            val item = itemDataBase.itemsDao.getItems()
            Timber.d("item: $item")

            if (item != null){
                emit(Resource.Success(data = item))
            }else{
                try {
                    val data = apiService.categories()
                    //itemDataBase.itemsDao.deleteItem()
                    Timber.d("data: $data")

                   val customItem = CustomItemEntity(
                       diagram = data.data.diagrams,
                       categories = data.data.categories
                   )

                    itemDataBase.itemsDao.insertItem(customItem)

                    val databaseItems = itemDataBase.itemsDao.getItems()
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


  suspend fun getParts(id:String): Flow<Resource<PartsEntity>>{
      return flow {
          emit(Resource.Loading())

          val parts = itemDataBase.partsDao.getParts()
          if (parts !=null){
              emit(Resource.Success(data = parts))
          }else{
              try {
                  val data = apiService.part(id)
                 itemDataBase.partsDao.insertParts(data)

                  val databaseParts = itemDataBase.partsDao.getParts()
                  if (databaseParts!=null){
                      emit(Resource.Success(data = databaseParts))
                  }

              }catch (e:HttpException){
                  emit(
                      Resource.Failure(
                          message = "Opps something went wrong",
                          data = parts
                      )
                  )
              }
          }
      }
  }
}