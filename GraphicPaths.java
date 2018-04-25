package application;

class GraphicPaths {
	private String FOLDER;
	private static final String TEMPORARY = "file:graphic/temporary/";
	private static final String FINAL = "file:graphic/final/";
	
	//environment
	private static final String BACKGROUND = "/backgrounds/back.jpg";
	private static final String CHEST = "environment/chest.png";
	private static final String CHEST_OPEN = "environment/chest_open.png";
	private static final String LADDER_LONG = "environment/ladder_long.png";
	private static final String LADDER_SHORT = "environment/ladder_short.png";
	private static final String LADDER_CLIMB = "environment/ladder_climb.jpg";
	
	//hero
	private static final String HERO_RIGHT = "character/character_right.png";
	private static final String HERO_LEFT = "character/character_left.png";
	private static final String HERO_BACK = "character/character_back.png";

	private static final String BEAR = "opponents/bear.png";
	
	//skills
	private static final String SKILL_ATTACK = "UI/attack_skill.jpg";
	private static final String SKILL_DEFENSE = "UI/defense_skill.jpg";
	private static final String SKILL_HEAL = "UI/healing_skill.jpg";
	private static final String SKILL_HIGHLIGHT = "UI/highlight.png";
	
	//ui
	private static final String PIECE_OF_HP = "UI/piece_of_hp.jpg";
	private static final String HP_STRIPE = "UI/hp_stripe.jpg";
	private static final String EKEY_LOOT = "UI/e-key_loot.png";
	private static final String EKEY_USE = "UI/e-key_use.png";
	
	private static final String BOUNDARY_MOVE = "other/boundary_move.jpg";
	private static final String BOUNDARY_STOP = "other/boundary_stop.jpg";
	
	public GraphicPaths() {
		boolean tmpTrueFinalFalse = false;
		if (tmpTrueFinalFalse)
			FOLDER = TEMPORARY;
		else
			FOLDER = FINAL;
	}
	
	public String getPath(String name) {
		switch (name) {
	        case "background":	return FOLDER+BACKGROUND;
	        case "chest":		return FOLDER+CHEST;
	        case "chestOpen":	return FOLDER+CHEST_OPEN;
	        case "ladderLong":		return FOLDER+LADDER_LONG;
	        case "ladderShort":		return FOLDER+LADDER_SHORT;
	        case "ladderClimb":		return FOLDER+LADDER_CLIMB;
	        
	        //hero
	        case "characterLeft":	return FOLDER+HERO_LEFT;
	        case "characterRight":	return FOLDER+HERO_RIGHT;
	        case "characterBack":	return FOLDER+HERO_BACK;

	        case "bear":	return FOLDER+BEAR;
	        
	        //skills
	        case "skillAttack":	return FOLDER+SKILL_ATTACK;
	        case "skillDefense":	return FOLDER+SKILL_DEFENSE;
	        case "skillHeal":	return FOLDER+SKILL_HEAL;
	        case "skillHightlight":	return FOLDER+SKILL_HIGHLIGHT;
	        
	        //ui
	        case "pieceOfHp":	return FOLDER+PIECE_OF_HP;
	        case "hpStripe":	return FOLDER+HP_STRIPE;
	        case "eKeyUse":		return FOLDER+EKEY_USE;
	        case "eKeyLoot":	return FOLDER+EKEY_LOOT;
	        
	        case "boundaryMove":	return FOLDER+BOUNDARY_MOVE;
	        case "boundaryStop":	return FOLDER+BOUNDARY_STOP;
	        default: return "";
    }
		
	}

}
