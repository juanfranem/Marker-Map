package com.example.data.shared.retrofit.config

import com.example.data.shared.retrofit.response.AssetMarkerResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET(ApiConstants.ROUTERS_MARKERS)
    suspend fun markers(
        @Path("city") city: String,
        @Query("lowerLeftLatLon") lowerLeftLatLon: String,
        @Query("upperRightLatLon") upperRightLatLon: String
    ): List<AssetMarkerResponse>

}