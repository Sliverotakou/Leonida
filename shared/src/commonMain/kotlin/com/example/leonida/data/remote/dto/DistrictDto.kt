package com.example.leonida.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Représente un quartier (District) tel que stocké dans la base de données.
 *
 * @property id Identifiant unique (UUID) agissant comme clé primaire.
 * @property name Nom affiché du quartier.
 * @property statut État du quartier : 'verrouille' ou 'actif'. Contrôle l'accès au contenu détaillé.
 * @property votesCount Compteur utilisé pour le mécanisme de vote communautaire.
 * @property dateUnlock Date de déblocage effectif (ISO 8601). Peut être nulle si non débloqué.
 */
@Serializable
data class DistrictDto(
    val id: String,
    val name: String,
    val statut: String,
    @SerialName("votes_count")
    val votesCount: Int,
    @SerialName("date_unlock")
    val dateUnlock: String?
)
