package com.dergruenkohl.hypixelapi.mapper

import com.dergruenkohl.hypixelapi.client.data.DungeonData
import com.dergruenkohl.hypixelapi.client.data.DungeonType
import com.dergruenkohl.hypixelapi.data.DungeonCompletion
import com.dergruenkohl.hypixelapi.data.DungeonCompletions
import com.dergruenkohl.hypixelapi.data.DungeonLevel
import com.dergruenkohl.hypixelapi.data.DungeonRuntime
import com.dergruenkohl.hypixelapi.data.Dungeons

object DungeonMapper {

    fun map(dungeonData: DungeonData): Dungeons {
        val catacombs = dungeonData.dungeonTypes.catacombs
        val masterCatacombs = dungeonData.dungeonTypes.masterCatacombs

        return Dungeons(
            level = DungeonLevel.fromExp(catacombs.experience),
            completions = DungeonCompletions(
                entrance = mapDungeonCompletion(catacombs, "0"),
                floor1 = mapDungeonCompletion(catacombs, "1"),
                floor2 = mapDungeonCompletion(catacombs, "2"),
                floor3 = mapDungeonCompletion(catacombs, "3"),
                floor4 = mapDungeonCompletion(catacombs, "4"),
                floor5 = mapDungeonCompletion(catacombs, "5"),
                floor6 = mapDungeonCompletion(catacombs, "6"),
                floor7 = mapDungeonCompletion(catacombs, "7"),
                masterMode1 = mapDungeonCompletion(masterCatacombs, "1"),
                masterMode2 = mapDungeonCompletion(masterCatacombs, "2"),
                masterMode3 = mapDungeonCompletion(masterCatacombs, "3"),
                masterMode4 = mapDungeonCompletion(masterCatacombs, "4"),
                masterMode5 = mapDungeonCompletion(masterCatacombs, "5"),
                masterMode6 = mapDungeonCompletion(masterCatacombs, "6"),
                masterMode7 = mapDungeonCompletion(masterCatacombs, "7")
            )
        )
    }

    private fun mapDungeonCompletion(dungeonType: DungeonType, floor: String): DungeonCompletion {
        return DungeonCompletion(
            bestRunTime = DungeonRuntime.fromSeconds(dungeonType.fastestTime[floor]?.toInt() ?: 0) ,
            bestRunTimeS = DungeonRuntime.fromSeconds(dungeonType.fastestTimeS[floor]?.toInt() ?: 0) ,
            bestRunTimeSPlus = DungeonRuntime.fromSeconds(dungeonType.fastestTimeSPlus[floor]?.toInt() ?: 0) ,
            timesBeaten = dungeonType.tierCompletions[floor]?.toInt() ?: 0
        )
    }
}
