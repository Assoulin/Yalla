package com.example.yalla.ui.models.services

const val API_URL = ""

class RestaurantsManager {
//    companion object {
//        fun fetchRestaurants(callback: (restaurants: List<Restaurant>) -> Unit) {
//            IO.fetchFromUrl(API_URL) { json ->
//                val jsonObject = JSONObject(json)
//                val resultArray = jsonObject.getJSONArray("restaurants")
//                val restaurants = mutableListOf<Restaurant>()
//                //Build the restaurants' item:
//                for (i in 0 until resultArray.length()) {
//                    val restaurantObject = resultArray.getJSONObject(i)
//                    val name = restaurantObject.getString("name")
//                    val description = restaurantObject.getString("description")
//                    val posterURL = URL(restaurantObject.getString("poster_url"))
//                    val isOn = restaurantObject.getBoolean("is_on")
//                    val categories = getCategories(restaurantObject.getJSONArray("categories"))
//                    val dishes = getDishes(restaurantObject.getJSONArray("dishes"))
//                    val deliveryPrice = restaurantObject.getDouble("delivery_price")
//                    restaurants.add(
//                        Restaurant(
//                            name,
//                            description,
//                            posterURL,
//                            isOn,
//                            categories,
//                            dishes,
//                            deliveryPrice
//                        )
//                    )
//                }
//                callback(restaurants)
//            }
//        }
//
//        private fun getDishes(dishObj: JSONArray): MutableList<Restaurant.Dish> {
//            val dishes = mutableListOf<Restaurant.Dish>()
//            for (i in 0 until dishObj.length()) {
//                with(dishObj.getJSONObject(i)) {
//                    dishes.add(
//                        Restaurant.Dish(
//                            this.getString("dish_name"),
//                            this.getString("dish_description"),
//                            this.getString("dish_photo"),
//                            this.getDouble("price"),
//                            this.getString("category"),
//                        )
//                    )
//                }
//            }
//            return dishes
//        }
//
//        private fun getCategories(categoriesObj: JSONArray): MutableList<String> {
//            val categories = mutableListOf<String>()
//            for (i in 0 until categoriesObj.length()) {
//                categories.add(categoriesObj.getString(i))
//            }
//            return categories
//        }
//    }
}