package application;

class GraphicPaths {
	private String FOLDER;
	private static final String TEMPORARY = "file:graphic/temporary/";
	private static final String FINAL = "file:graphic/final/";
	private static final String BACKGROUND = "/backgrounds/back.jpg";
	private static final String CHEST = "environment/chest.png";
	private static final String CHEST_OPEN = "environment/chest_open.png";
	private static final String LADDER = "environment/ladder.jpg";
	private static final String LADDER_CLIMB = "environment/ladder_climb.jpg";
	private static final String HERO_RIGHT = "character/character_right.png";
	private static final String HERO_LEFT = "character/character_left.png";
	private static final String HERO_BACK = "character/character_back.png";
	private static final String EKEY_LOOT = "UI/e-key_loot.png";
	private static final String EKEY_USE = "UI/e-key_use.png";
	private static final String BEAR = "opponents/bear.jpg";
	private static final String SKILL_ATTACK = "UI/skill_1.jpg";
	private static final String SKILL_DEFENSE = "UI/skill_2.jpg";
	private static final String SKILL_HEAL = "UI/skill_3.jpg";
	private static final String SKILL_HIGHLIGHT = "UI/highlight.png";
	private static final String PIECE_OF_HP = "UI/piece_of_hp.jpg";
	
	public GraphicPaths() {
		boolean tmpTrueFinalFalse = true;
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
	        case "ladder":		return FOLDER+LADDER;
	        case "ladderClimb":		return FOLDER+LADDER_CLIMB;
	        case "characterLeft":	return FOLDER+HERO_LEFT;
	        case "characterRight":	return FOLDER+HERO_RIGHT;
	        case "characterBack":	return FOLDER+HERO_BACK;
	        case "eKeyUse":		return FOLDER+EKEY_USE;
	        case "eKeyLoot":	return FOLDER+EKEY_LOOT;
	        case "bear":	return FOLDER+BEAR;
	        case "skillAttack":	return FOLDER+SKILL_ATTACK;
	        case "skillDefense":	return FOLDER+SKILL_DEFENSE;
	        case "skillHeal":	return FOLDER+SKILL_HEAL;
	        case "skillHightlight":	return FOLDER+SKILL_HIGHLIGHT;
	        case "pieceOfHp":	return FOLDER+PIECE_OF_HP;
	        default: return "";
    }
		
	}

}
