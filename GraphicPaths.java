package application;

class GraphicPaths {
	private String FOLDER;
	private static final String TEMPORARY = "file:graphic/temporary/";
	private static final String FINAL = "file:graphic/isometric/";
	
	//main menu
	private static final String MAIN_MENU_BACKGROUND = "/mainMenu/background.jpg";
	private static final String MAIN_MENU_OPTIONS = "/mainMenu/options.png";
	private static final String MAIN_MENU_HIGHLIGHT = "/mainMenu/highlight.png";
	
	//environment
	private static final String MAP = "/backgrounds/map.jpg";
	
	private static final String CHEST = "environment/chest.png";
	private static final String CHEST_OPEN = "environment/chest_open.png";
	private static final String LADDER = "environment/ladder.jpg";
	private static final String LADDER_SHORT = "environment/ladder_short.png";
	
	//hero
	private static final String HERO_FRONT = "character/character_front.png";
	private static final String HERO_RIGHT = "character/character_right.png";
	private static final String HERO_LEFT = "character/character_left.png";
	private static final String HERO_BACK = "character/character_back.png";
	private static final String HERO_FLOOR = "character/character_floor.png";
	
	//bandit
	private static final String BANDIT_FRONT = "opponents/bandit_front.png";
	private static final String BANDIT_RIGHT = "opponents/bandit_right.png";
	private static final String BANDIT_LEFT = "opponents/bandit_left.png";
	private static final String BANDIT_BACK = "opponents/bandit_back.png";
	private static final String BANDIT_FLOOR = "opponents/bandit_floor.png";
	
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
	
	private static final String STOPPER_MAIN_VERTICAL = "stoppers/main_vertical.jpg";
	private static final String STOPPER_MAIN_HORIZONTAL = "stoppers/main_horizontal.jpg";

	
	public GraphicPaths() {
		boolean tmpTrueFinalFalse = false;
		if (tmpTrueFinalFalse)
			FOLDER = TEMPORARY;
		else
			FOLDER = FINAL;
	}
	
	public String getPath(String name) {
		switch (name) {
			//main menu
        	case "mainMenuBackground":	return FOLDER+MAIN_MENU_BACKGROUND;
        	case "mainMenuOptions":	return FOLDER+MAIN_MENU_OPTIONS;
        	case "mainMenuHighlight":	return FOLDER+MAIN_MENU_HIGHLIGHT;
		
	        case "map":	return FOLDER+MAP;
	        
	        case "chest":		return FOLDER+CHEST;
	        case "chestOpen":	return FOLDER+CHEST_OPEN;
	        case "ladder":		return FOLDER+LADDER;
	        case "ladderShort":		return FOLDER+LADDER_SHORT;
	        
	        //hero
	        case "characterFront":	return FOLDER+HERO_FRONT;
	        case "characterLeft":	return FOLDER+HERO_LEFT;
	        case "characterRight":	return FOLDER+HERO_RIGHT;
	        case "characterBack":	return FOLDER+HERO_BACK;
	        case "characterFloor":	return FOLDER+HERO_FLOOR;
	        
	        //opponent
	        case "banditFront":	return FOLDER+BANDIT_FRONT;
	        case "banditLeft":	return FOLDER+BANDIT_LEFT;
	        case "banditRight":	return FOLDER+BANDIT_RIGHT;
	        case "banditBack":	return FOLDER+BANDIT_BACK;
	        case "banditFloor":	return FOLDER+BANDIT_FLOOR;
	        
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
	        
	        //stoppers
	        case "stopperMainHorizontal":	return FOLDER+STOPPER_MAIN_HORIZONTAL;
	        case "stopperMainVertical":	return FOLDER+STOPPER_MAIN_VERTICAL;
	        default: return "";
    }
		
	}

}
