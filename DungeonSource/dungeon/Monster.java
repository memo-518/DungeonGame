package dungeon;


/**
 * Title:
 * Description:
 * Copyright:    Copyright (c) 2001
 * Company:
 * @author
 * @version 1.0
 */


public abstract class Monster extends DungeonCharacter
{
	protected double chanceToHeal;
	protected int minHeal, maxHeal;
	protected HealBehavior healBehavior;

//-----------------------------------------------------------------
  public Monster(String name, int hitPoints, int attackSpeed,
				     double chanceToHit, double chanceToHeal,
					 int damageMin, int damageMax,
					 int minHeal, int maxHeal)
  {
	super(name, hitPoints, attackSpeed, chanceToHit, damageMin, damageMax);
	this.chanceToHeal = chanceToHeal;
	this.maxHeal = maxHeal;
	this.minHeal = minHeal;
	
	this.healBehavior = new MonsterBasicHealBehavior();
	AttackFactory attacks = AttackFactory.getAttackFactory();
	this.attackBehavior = attacks.getAttack("Base Attack");

  }//end monster constructor
  
  public int getMinHeal()
  {
	  return this.minHeal;
  }
  
  public int getMaxHeal()
  {
	  return this.maxHeal;
  }
  
  public double getChanceToHeal()
  {
	  return this.chanceToHeal;
  }
  
  //Robin Deskins note: This is for testing purposes to bypass rng with healing
  public void setChanceToHeal(double healChance) {
	  this.chanceToHeal = healChance;
  }

//-----------------------------------------------------------------
  public void heal()
  {
	  this.healBehavior.heal(this);
  }//end heal method

//-----------------------------------------------------------------
 public void subtractHitPoints(int hitPoints)
 {
		super.subtractHitPoints(hitPoints);
		heal();

 }//end method


}//end Monster class