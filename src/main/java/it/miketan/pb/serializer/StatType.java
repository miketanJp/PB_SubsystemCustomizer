package it.miketan.pb.serializer;

public enum StatType {

   TAGS("tags"),
   STATDISTRIBUTION("stat_distribution"),
   STATS("stats"),
   TARGETMODE("targetMode"),
   TARGETSOCKET("targetSocket"),
   TARGETHARDPOINT("targetHardpoint"),
   VALUE("value"),
   ACT_COUNT("act_count"),
   ACT_DURATION("act_duration"),
   ACT_HEAT("act_heat"),
   MASS("mass"),
   SCRAP_VALUE("value"),
   WPN_CONCUSSION("wpn_concussion"),
   WPN_DAMAGE("wpn_damage"),
   WPN_DAMAGE_RADIUS("wpn_damage_radius"),
   WPN_IMPACT("wpn_impact"),
   WPN_IMPACT_RADIUS("wpn_impact_radius"),
   WPN_PROJ_LIFETIME("wpn_proj_lifetime"),
   WPN_PROJ_RICOCHET("wpn_proj_ricochet"),
   WPN_RANGE_MAX("wpn_range_max"),
   WPN_RANGE_MIN("wpn_range_min"),
   WPN_SCATTER_ANGLE("wpn_scatter_angle"),
   WPN_SCATTER_ANGLE_MOVING("wpn_scatter_angle_moving"),
   WPN_SPEED("wpn_speed");

   StatType(String stat) {

   }


}
