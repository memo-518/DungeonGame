package dungeon;
import java.util.Scanner;

/**
 * Title: Dungeon.java
 *
 * Description: Driver file for Heroes and Monsters project
 *
 * Copyright:    Copyright (c) 2001
 * Company: Code Dogs Inc.
 * I.M. Knurdy
 *
 * History:
 *  11/4/2001: Wrote program
 *    --created DungeonCharacter class
 *    --created Hero class
 *    --created Monster class
 *    --had Hero battle Monster
 *    --fixed attack quirks (dead monster can no longer attack)
 *    --made Hero and Monster abstract
 *    --created Warrior
 *    --created Ogre
 *    --made Warrior and Ogre battle
 *    --added battleChoices to Hero
 *    --added special skill to Warrior
 *    --made Warrior and Ogre battle
 *    --created Sorceress
 *    --created Thief
 *    --created Skeleton
 *    --created Gremlin
 *    --added game play features to Dungeon.java (this file)
 *  11/27/2001: Finished documenting program
 * version 1.0
 */



/*
  This class is the driver file for the Heroes and Monsters project.  It will
  do the following:

  1.  Allow the user to choose a hero
  2.  Randomly select a monster
  3.  Allow the hero to battle the monster

  Once a battle concludes, the user has the option of repeating the above

*/
public class DungeonAdventure
{
	static Scanner kb = new Scanner(System.in);

    public static void main(String[] args)
	{

		System.out.print("Welcome to the pillars of OO a dungeon adventure game!");
		do
		{
		    play();

		} while (playAgain());

    }//end main method

/*-------------------------------------------------------------------
chooseHero allows the user to select a hero, creates that hero, and
returns it.  It utilizes a polymorphic reference (Hero) to accomplish
this task
---------------------------------------------------------------------*/
    
	private static Hero chooseHero()
	{
		int choice = -1;

		
		System.out.println("Choose a hero:\n" +
					       "1. Warrior\n" +
						   "2. Sorceress\n" +
						   "3. Thief\n" +
						   "4. Monk\n" +
						   "5. Dinosaur\n");
		
		
		//While the choice is outside of the range of the possible attacks array
		while(choice < 1 || choice > 5)
		{
			try
			{
				System.out.print("Enter your choice: ");
				choice = Integer.parseInt(kb.next());
				kb.nextLine();
			}
			catch(Exception e)
			{
				System.out.println("invalid choice!");
			}
		}
		
		String name;
		System.out.print("Enter character name: ");
		name = kb.nextLine();
		switch(choice)
		{
			case 1: return HeroFactory.createHero("Warrior", name);

			case 2: return HeroFactory.createHero("Sorceress", name);

			case 3: return HeroFactory.createHero("Thief", name);
			
			case 4: return HeroFactory.createHero("Monk",name);
			
			case 5: return HeroFactory.createHero("Dinosaur", name);

			default: System.out.println("invalid choice, returning Thief");
			return HeroFactory.createHero("Thief", name);
		}//end switch
	}//end chooseHero method

/*-------------------------------------------------------------------
playAgain allows gets choice from user to play another game.  It returns
true if the user chooses to continue, false otherwise.
---------------------------------------------------------------------*/
	private static boolean playAgain()
	{
		String again;

		System.out.println("Play again (y/n)?");
		again = kb.next();
		
		//Updates playAgain() method to handle proper string input reading
		return (again.equals("Y") || again.equals("y"));
	}//end playAgain method


/*-------------------------------------------------------------------
battle is the actual combat portion of the game.  It requires a Hero
and a Monster to be passed in.  Battle occurs in rounds.  The Hero
goes first, then the Monster.  At the conclusion of each round, the
user has the option of quitting.
---------------------------------------------------------------------*/
	private static void battle(Hero theHero, Monster theMonster)
	{
		String pause = "p";
		System.out.println(theHero.getName() + " battles " +
							theMonster.getName());
		System.out.println("---------------------------------------------");

		//do battle
		while (theHero.isAlive() && theMonster.isAlive() && !pause.equals("q"))
		{
		    //hero goes first
			theHero.battleChoices(theMonster,kb);

			//monster's turn (provided it's still alive!)
			if (theMonster.isAlive())
			    theMonster.attack(theHero);

			//let the player bail out if desired
			System.out.print("\n-->q to quit, anything else to continue: ");
			pause = kb.next();

		}//end battle loop

		if (!theMonster.isAlive())
		    System.out.println(theHero.getName() + " was victorious!");
		else if (!theHero.isAlive())
			System.out.println(theHero.getName() + " was defeated :-(");
		else//both are alive so user quit the game
			System.out.println("Quitters never win ;-)");

	}//end battle method

	public static void play()
	{
		Hero theHero;
		theHero = chooseHero();
	    System.out.println("the mighty " + theHero.name + " enters the dungeon" );
	}
	private void saveGame()
	{
		//to be added by memento
	}
	private void loadGame()
	{
		//to be added by memento
	}
	
}//end Dungeon class