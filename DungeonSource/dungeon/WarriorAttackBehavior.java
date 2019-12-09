package dungeon;

import java.io.Serializable;

public class WarriorAttackBehavior implements AttackBehavior, Serializable {

	private BaseAttackBehavior baseAttack = new BaseAttackBehavior();
	
    @Override
    public void attack(DungeonCharacter hero, String displayName, DungeonCharacter opponent)
    {
		System.out.println(displayName + " swings their sword at " +
				opponent.getName() + ":");
		
		//Call to base attack behavior, common to all hero types
		this.baseAttack.attack(hero, displayName, opponent);
        
    }//end override of attack method

    @Override
    public String toString()
    {
        return "Attack opponent";
    }
}