package com.example.leonida.data.remote.dto

import com.example.leonida.domain.enums.PoisSource
import com.example.leonida.domain.enums.PoisType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data Transfer Object representing a Point of Interest (POI) from the remote database.
 *
 * @property id Primary Key (UUID).
 * @property districtId Foreign Key referencing the districts table (quartiers.id).
 * @property type Category of the POI (e.g., weapon, jump, rare vehicle, collectible).
 * @property name Display name of the location.
 * @property brief Field note providing additional context or descriptions.
 * @property butinEstime Estimated value or reward found at this location.
 * @property coordinates Native PostGIS spatial coordinates (Point, 4326).
 * @property etage Floor level information (e.g., Ground Floor, Basement, Penthouse).
 * @property difficultyTag Aggregated community tag reflecting the difficulty or alert status.
 * @property source Data source origin: 'admin' or 'communaute'.
 * @property sourceSubmissionId Reference to the original submission if source is 'communaute'.
 * @property isPublic Visibility flag. Controlled by backend triggers; never toggled directly by the mobile client.
 */
@Serializable
data class PoisDto(
    val id: String,
    @SerialName("district_id")
    val districtId: String,
    val type: PoisType,
    val name: String,
    val brief: String?,
    @SerialName("butin_estime")
    val butinEstime: String?,
    val coordinates: String,
    @SerialName("floor")
    val etage: String?,
    @SerialName("difficulty_tag")
    val difficultyTag: String?,
    val source: PoisSource,
    @SerialName("source_submission_id")
    val sourceSubmissionId: String?,
    @SerialName("is_public")
    val isPublic: Boolean
)
