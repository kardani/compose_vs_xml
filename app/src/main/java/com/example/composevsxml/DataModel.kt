package com.example.composevsxml

import java.io.Serializable
import kotlin.random.Random

sealed class DataModel(
    open val id: String,
    open val userFullName: String,
    open val userImageUrl: String,
    open val rate: Float,
    open val totalChallenges: Int,
    open val completedChallenges: Int,
) : Serializable{

    data class Small(
        override val id: String,
        override val userFullName: String,
        override val userImageUrl: String,
        override val rate: Float,
        override val totalChallenges: Int,
        override val completedChallenges: Int,
    ): DataModel(id, userFullName, userImageUrl, rate, totalChallenges, completedChallenges)

    data class Large(
        override val id: String,
        override val userFullName: String,
        override val userImageUrl: String,
        override val rate: Float,
        override val totalChallenges: Int,
        override val completedChallenges: Int,
    ): DataModel(id, userFullName, userImageUrl, rate, totalChallenges, completedChallenges)

    companion object{
        fun generateDummyDataList(count: Int = 20): List<DataModel>{
            val list = mutableListOf<DataModel>()

            for (i in 0 until count){

                val randomType = Random.nextBoolean()

                list.add(

                    generateDummyData(
                        randomType,
                        i
                    )

//                    if(randomType) {
//                        Large(
////                            id = generateRandomString(
////                                64,
////                                includeSpecialChar = true,
////                                includeNumbers = true
////                            ),
//                            id = i.toString(),
//                            userFullName = generateRandomFullName(),
//                            userImageUrl = "https://picsum.photos/id/${1000 + imageCat}/300/300",
//                            rate = Random.nextFloat() * 5,
//                            totalChallenges = totalChallenges,
//                            completedChallenges = completedChallenges
//                        )
//                    }else{
//                        Small(
////                            id = generateRandomString(
////                                64,
////                                includeSpecialChar = true,
////                                includeNumbers = true
////                            ),
//                            id = i.toString(),
//                            userFullName = generateRandomFullName(),
//                            userImageUrl = "https://picsum.photos/id/${1000 + imageCat}/300/300",
//                            rate = Random.nextFloat() * 5,
//                            totalChallenges = totalChallenges,
//                            completedChallenges = completedChallenges
//                        )
//                    }
                )
            }
            return list
        }

        fun generateDummyData(
            type: Boolean = Random.nextBoolean(),
            id: Int
        ): DataModel{

            val totalChallenges = Random.nextInt(2, 10)
            val completedChallenges = Random.nextInt(0, totalChallenges)
            val imageCat = Random.nextInt(1, 50)

            return if(type) {
                Large(
                    id = id.toString(),
                    userFullName = generateRandomFullName(),
                    userImageUrl = "https://picsum.photos/id/${1000 + imageCat}/300/300",
                    rate = Random.nextFloat() * 5,
                    totalChallenges = totalChallenges,
                    completedChallenges = completedChallenges
                )
            }else{
                Small(
                    id = id.toString(),
                    userFullName = generateRandomFullName(),
                    userImageUrl = "https://picsum.photos/id/${1000 + imageCat}/300/300",
                    rate = Random.nextFloat() * 5,
                    totalChallenges = totalChallenges,
                    completedChallenges = completedChallenges
                )
            }

        }
    }
}
