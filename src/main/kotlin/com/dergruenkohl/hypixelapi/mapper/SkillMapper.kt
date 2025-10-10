package com.dergruenkohl.hypixelapi.mapper

import com.dergruenkohl.hypixelapi.client.data.SkyblockProfileMemberSkills
import com.dergruenkohl.hypixelapi.data.Skill
import com.dergruenkohl.hypixelapi.data.Skills

object SkillMapper {
    fun fromSkyblockProfileMemberSkills(memberSkills: SkyblockProfileMemberSkills): Skills {
        return Skills(
            fishing = Skill.fromExp(memberSkills.fishing),
            alchemy = Skill.fromExp(memberSkills.alchemy),
            runecrafting = Skill.fromExp(memberSkills.runecrafting),
            mining = Skill.fromExp(memberSkills.mining),
            farming = Skill.fromExp(memberSkills.farming),
            enchanting = Skill.fromExp(memberSkills.enchanting),
            taming = Skill.fromExp(memberSkills.taming),
            foraging = Skill.fromExp(memberSkills.foraging),
            social = Skill.fromExp(memberSkills.social),
            carpentry = Skill.fromExp(memberSkills.carpentry),
            combat = Skill.fromExp(memberSkills.combat)
        )
    }
}
