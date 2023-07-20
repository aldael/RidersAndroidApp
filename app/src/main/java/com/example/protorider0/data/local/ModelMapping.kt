package com.example.protorider0.data.local

import com.example.protorider0.model.Rider

fun Rider.toLocal() = RiderLocal(
    id = id,
    rider = rider,
    identidad = identidad,
    armor = armor,
    serie = serie
)

fun List<Rider>.toLocal() = map(Rider::toLocal)

fun RiderLocal.toExternal() = Rider(
    id = id,
    rider = rider,
    identidad = identidad,
    armor = armor,
    serie = serie
)

fun List<RiderLocal>.toExternal() = map(RiderLocal::toExternal)