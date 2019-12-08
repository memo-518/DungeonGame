package dungeon;

import java.util.Random;

public class Dungeon {
	private Room [][] dungeonRooms;
	private int potions; 
	private int pits;
	private int monsters;
	private Room heroLocation;
	private String state;
	
	
	private Dungeon()
	{
		
	}
	public void createDungeon()
	{
		this.dungeonRooms = new Room[5][5];
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				dungeonRooms[i][j] = new Room(j,i);
			}
		}
	}
	public void setUpDungeon(Hero hero)
	{
		Room entrance = new Room(this.heroLocation.getX(),this.heroLocation.getY());
		
		int x,y,i = 0;
		Random RAND = new Random(); 
		Room newRoom;
		boolean success = false;
		while(!success)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			newRoom = new Room(x,y);
			if(!(entrance.getX() == x) && !(entrance.getY() == y))
				{
					setExit(newRoom);
					success = true;
				}
			
		}
		i =0;
		//add pillars
		while(i > 4)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			newRoom = new Room(x,y);
			addPillars(newRoom);
			if(dungeonRooms[x][y].isEmpty())
			{
				dungeonRooms[x][y] = newRoom;
				i++;
			}
			
		}
		i=0;
		//add monsters
		while(i < 3)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			newRoom = new Room(x,y);
			newRoom.addMonster();
			if(dungeonRooms[x][y].isEmpty())
			{
				dungeonRooms[x][y] = newRoom;
				i++;
			}
			
		}
		i=0;
		//add items
		while(i < 4)
		{
			x = RAND.nextInt(4);
			y = RAND.nextInt(4);
			newRoom = new Room(x,y);
			addItems(newRoom);
			if(dungeonRooms[x][y].isEmpty())
			{
				dungeonRooms[x][y] = newRoom;
				i++;
			}
		}
		
		
		
	}

	public  String toString()
	{
		String dungeon = "";
		int i =0;
		int j =0;
		while(i < 5)
		{
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].StringTop();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].stringMid();
				j++;
			}
			dungeon += "\n";
			j =0;
			while(j < 5)
			{
				dungeon += dungeonRooms[j][i].stringBottom();
				j++;
			}
			dungeon += "\n";
			i++;
			
		}
		return dungeon;
		
	}
	private void setExit(Room r)
	{
		r.setExit();
	}
	private boolean addPillars(Room r)
	{
		return r.addItem(new Item(r,"pillar"));
	}
	private boolean addMonsters(Room r)
	{
		if(r.getMonster().equals(null))
		{
			r.addMonster();
			return true;
		}
		return false;
	}
	private boolean addItems(Room r)
	{
		if(!(r.addItem(new Item(r,"potion"))))
		{
			if(!(r.addItem(new Item(r,"pit"))))
			{
				return false;
			}
		}
		return true;
		
	}
	public Memento saveDungeon()
	{
		return null;
		
	}
	public Memento loadDungeon()
	{
		return null;
		
	}
}
